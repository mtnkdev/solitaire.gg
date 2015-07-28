package controllers.admin

import java.util.UUID

import akka.pattern.ask
import akka.util.Timeout
import controllers.BaseController
import models._
import play.api.i18n.MessagesApi
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import services.database.Schema
import services.scheduled.ScheduledTask
import services.supervisor.ActorSupervisor
import services.user.{ UserService, AuthenticationEnvironment }

import scala.concurrent.Future
import scala.concurrent.duration._
import scala.util.Random

@javax.inject.Singleton
class AdminController @javax.inject.Inject() (
    override val messagesApi: MessagesApi,
    override val env: AuthenticationEnvironment,
    scheduledTask: ScheduledTask
) extends BaseController {
  implicit val timeout = Timeout(10.seconds)

  def index = withAdminSession("index") { implicit request =>
    Future.successful(Ok(views.html.admin.index()))
  }

  def enable = withSession("admin.enable") { implicit request =>
    UserService.enableAdmin(request.identity).map { response =>
      Ok(response)
    }
  }

  def status = withAdminSession("status") { implicit request =>
    (ActorSupervisor.instance ask GetSystemStatus).map {
      case x: SystemStatus => Ok(views.html.admin.activity.status(x))
    }
  }

  def notifyAllForm = withAdminSession("notfy-all") { implicit request =>
    Future.successful(Ok(views.html.admin.activity.notifyForm()))
  }

  def notification(recipient: String) = withAdminSession("send-notification") { implicit request =>
    val message = request.body.asFormUrlEncoded.map { body =>
      body("message").headOption.getOrElse(throw new IllegalStateException())
    }.getOrElse(throw new IllegalStateException())

    val msg = Notification(if(recipient == "all") { None } else { Some(UUID.fromString(recipient)) }, message)
    (ActorSupervisor.instance ask msg).map {
      case _ => Ok(s"Notification [$message] sent to [$recipient].")
    }
  }

  def wipeSchema(key: String) = withAdminSession("wipe.schema") { implicit request =>
    if (key == "for realz") {
      Schema.wipe().map { tables =>
        Ok(s"OK, Wiped tables [${tables.mkString(", ")}].")
      }
    } else {
      Future.successful(NotFound("Missing key."))
    }
  }

  def observeRandomGame() = withAdminSession("observe.random") { implicit request =>
    (ActorSupervisor.instance ask GetSystemStatus).map {
      case ss: SystemStatus => if (ss.games.isEmpty) {
        Ok("No games available.")
      } else {
        val gameId = ss.games(new Random().nextInt(ss.games.length))._1
        Ok(views.html.game.gameplay(s"Observing [$gameId]", request.identity, "", "", Seq("observe", gameId.toString)))
      }
      case se: ServerError => Ok(s"${se.reason}: ${se.content}")
    }
  }

  def observeGameAsAdmin(gameId: UUID) = withAdminSession("observe.game") { implicit request =>
    Future.successful(Ok(views.html.game.gameplay(s"Observing [$gameId]", request.identity, "", "", Seq("observe", gameId.toString))))
  }

  def observeGameAs(gameId: UUID, as: UUID) = withAdminSession("observe.game.as") { implicit request =>
    Future.successful(Ok(views.html.game.gameplay(s"Observing [$gameId]", request.identity, "", "", Seq("observe", gameId.toString, as.toString))))
  }
}
