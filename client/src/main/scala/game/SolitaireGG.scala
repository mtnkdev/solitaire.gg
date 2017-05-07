package game

import java.util.UUID

import help.HelpService
import models.game.GameStateDebug
import msg.SocketMessage
import navigation.{MenuService, NavigationService, NavigationState}
import network.NetworkService
import org.scalajs.dom
import org.scalajs.dom.raw.BeforeUnloadEvent
import phaser.PhaserGame
import phaser.gameplay.InputHelper
import settings.SettingsService
import utils.{Logging, NullUtils}

import scala.scalajs.js
import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}
import scala.util.Random

@JSExportTopLevel("SolitaireGG")
object SolitaireGG {
  private[this] var active: Option[SolitaireGG] = None
  def getActive = active.getOrElse(throw new IllegalStateException("No active application."))

  @JSExport
  def go(debug: Boolean): Unit = active match {
    case None => active = Some(new SolitaireGG(debug))
    case _ => throw new IllegalStateException("Already initialized.")
  }
}

class SolitaireGG(val debug: Boolean) {
  private[this] var game: Option[ActiveGame] = None
  def hasGame = game.isDefined
  def getGame = game.getOrElse(throw new IllegalStateException("No active game."))
  def clearGame() = game = None
  def setGame(g: ActiveGame) = game = Some(g)

  val navigation = new NavigationService(onStateChange)
  val network = new NetworkService(debug, handleSocketMessage)
  val settings = new SettingsService()
  val menu = new MenuService(settings, navigation)

  val phaser = new PhaserGame(this)

  init()

  private[this] def onStateChange(o: NavigationState, n: NavigationState, args: Seq[String]): Unit = {
    o match {
      case NavigationState.Loading => navigation.setNavPosition(shown = true, top = true)
      case _ => // noop
    }
    n match {
      case NavigationState.List => GameListService.initIfNeeded(rules => {
        phaser.gameplay.activeGame.foreach { gameId =>
          GameStartService.endGame(this, gameId, win = false)
        }
        GameStartService.onGameStateChange(this, Seq(rules))
      })
      case NavigationState.Game => GameStartService.onGameStateChange(this, args)
      case NavigationState.Help => HelpService.show("klondike")
      case _ => // noop
    }
  }

  private[this] def init() = {
    utils.Logging.info("Solitaire.gg, v2.0.0")
    Logging.installErrorHandler()
    js.Dynamic.global.PhaserGlobal = js.Dynamic.literal("hideBanner" -> true)

    dom.window.onbeforeunload = (e: BeforeUnloadEvent) => {
      game match {
        case Some(_) => NullUtils.inst //"You're playing a game. Are you sure you'd like to resign?"
        case _ => NullUtils.inst
      }
    }

    phaser.start()
  }

  private[this] def handleSocketMessage(msg: SocketMessage) = {
    utils.Logging.info(s"SocketMessage: [$msg].")
  }

  def onSandbox() = {
    utils.Logging.info(GameStateDebug.toString(phaser.gameplay.services.state))
    phaser.gameplay.activeGame.foreach { gameId =>
      GameStartService.endGame(this, gameId, win = true)
    }
    GameStartService.startGame(this, UUID.randomUUID, "klondike", Math.abs(Random.nextInt))
  }

  def onPhaserLoadComplete(): Unit = {
    new InputHelper(phaser)
    navigation.initialAction()
  }
}