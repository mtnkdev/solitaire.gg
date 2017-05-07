package phaser.playmat

import com.definitelyscala.phaser.Group
import models.GameWon
import models.pile.set.PileSet
import phaser.PhaserGame
import phaser.card.CardSprite
import phaser.pile.PileGroup
import utils.NullUtils

import scala.scalajs.js.annotation.ScalaJSDefined

@ScalaJSDefined
class Playmat(val phaser: PhaserGame, val pileSets: Seq[PileSet], val layoutString: String) extends Group(
  game = phaser, parent = NullUtils.inst, name = "playmat"
) {
  var w: Double = 0.0
  var h: Double = 0.0

  private[this] var cards = Map.empty[Int, (CardSprite, PileGroup, Int)]
  def getCardSprite(id: Int) = cards(id)._1
  def getCards = cards.values.toSeq
  def setCards(c: Map[Int, (CardSprite, PileGroup, Int)]) = cards = c

  val emitter = new PlaymatEmitter(this)

  val resizer = new PlaymatResizer(this)
  resizer.refreshLayout(animate = false)

  phaser.add.existing(this)

  private[this] val pileGroups = collection.mutable.HashMap.empty[String, PileGroup]

  def getPileGroups = pileGroups
  def getPileGroup(id: String) = pileGroups.getOrElse(id, throw new IllegalStateException(s"Pile group [$id] not found."))

  def addPile(pileGroup: PileGroup) = {
    pileGroups(pileGroup.id) = pileGroup
    val pileLocation = resizer.layout._3.getOrElse(pileGroup.id, throw new IllegalStateException(s"Missing layout for [${pileGroup.pile.id}]."))
    pileGroup.x = pileLocation._1 * phaser.getSettings.cardSet.cardWidth
    pileGroup.y = pileLocation._2 * phaser.getSettings.cardSet.cardHeight
  }

  def initialMovesComplete() = {
    pileGroups.foreach { pile =>
      pile._2.cards.foreach { card =>
        card.bringToTop()
      }
    }
  }

  def win(gw: GameWon) = utils.Logging.info("Win!")
  def lose() = utils.Logging.info("Lose!")
}