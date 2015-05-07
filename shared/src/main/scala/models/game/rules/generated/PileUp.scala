// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Foundation Sets (Fn): 0
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau initial cards (T0d): -2 (custom)
 *   Custom initial cards (T0ds): UUUU UUUU UUUU UUUU UUUU UUUU UUUU UUUU UUUU UUUU UUUU UUUU UUUU
 *   Maximum cards per tableau (T0m): 4 (4 cards)
 *   Tableau piles (T0n): 15
 *   Tableau rank match rule for building (T0r): 0x0040
 *   Tableau suit match rule for building (T0s): 5 (Regardless of suit)
 *   Tableau rank match rule for moving stacks (T0tr): 0x0040
 *   Tableau suit match rule for moving stacks (T0ts): 5 (Regardless of suit)
 *   Number of waste piles (W0n): 0
 *   Victory condition (victory): 3 (All cards on tableau sorted)
 *   *vrank (vrank): 0x0040
 *   *vsuit (vsuit): 5
 */
object PileUp extends GameRules(
  id = "pileup",
  title = "Pile Up",
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/fifteen_puzzle.htm"),
    Link("AisleRiot", "help.gnome.org/users/aisleriot/stable/Pileon.html.en")
  ),
  description = "A game where you must sort the cards by rank rather than suit.",
  victoryCondition = VictoryCondition.AllOnTableauSorted,
  tableaus = Seq(
    TableauRules(
      numPiles = 15,
      initialCards = InitialCards.Custom,
      customInitialCards = Seq(
        "UUUU",
        "UUUU",
        "UUUU",
        "UUUU",
        "UUUU",
        "UUUU",
        "UUUU",
        "UUUU",
        "UUUU",
        "UUUU",
        "UUUU",
        "UUUU",
        "UUUU"
      ),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      rankMatchRuleForBuilding = RankMatchRule.Equal,
      suitMatchRuleForMovingStacks = SuitMatchRule.Any,
      rankMatchRuleForMovingStacks = RankMatchRule.Equal,
      emptyFilledWith = FillEmptyWith.Aces,
      maxCards = 4
    )
  ),
  complete = false
)

