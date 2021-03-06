package util

import java.util.TimeZone

import akka.actor.{ActorSystem, Props}
import com.codahale.metrics.SharedMetricRegistries
import play.api.i18n.MessagesApi
import play.api.inject.ApplicationLifecycle
import play.api.libs.ws.WSClient
import services.audit.NotificationService
import services.database.{Database, Schema}
import services.file.FileService
import services.scheduled.ScheduledTask
import services.supervisor.ActorSupervisor
import util.metrics.Instrumented

import scala.concurrent.Future

object Application {
  var initialized = false
}

@javax.inject.Singleton
class Application @javax.inject.Inject() (
    val config: util.Config,
    val messagesApi: MessagesApi,
    val lifecycle: ApplicationLifecycle,
    val notificationService: NotificationService,
    val system: ActorSystem,
    val task: ScheduledTask,
    val ws: WSClient
) extends Logging {
  if (Application.initialized) {
    log.info("Skipping initialization after failure.")
  } else {
    start()
  }

  val supervisor = system.actorOf(Props(classOf[ActorSupervisor], this), "supervisor")
  log.debug(s"Actor Supervisor [${supervisor.path}] started for [${util.Config.projectId}].")

  protected[this] def start() = {
    if (Application.initialized) {
      throw new IllegalStateException("ApplicationContext is already initialized.")
    }
    Application.initialized = true

    log.info(s"${Config.projectName} is starting on [${config.hostname}].")

    TimeZone.setDefault(TimeZone.getTimeZone("UTC"))
    System.setProperty("user.timezone", "UTC")

    SharedMetricRegistries.remove("default")
    SharedMetricRegistries.add("default", Instrumented.metricRegistry)

    Database.open(config.underlying())
    Schema.update()

    FileService.setRootDir(config.fileCacheDir)

    if (config.adminKey == "admin") {
      log.warn("Using default admin key. This installation is not secure.")
    }

    if (!config.debug) {
      scheduleTask(task, system)
    }

    lifecycle.addStopHook(() => Future.successful(stop()))
  }

  private[this] def stop() = {
    Database.close()
    SharedMetricRegistries.remove("default")
  }

  private[this] def scheduleTask(task: ScheduledTask, system: ActorSystem) = {
    import util.FutureUtils.defaultContext
    import scala.concurrent.duration._
    log.info("Scheduling task to run every hour, after five minutes.")
    system.scheduler.schedule(5.minutes, 1.hour, task)
  }
}
