// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Auto-move cards to foundation (F0a): 1 (Whenever possible)
 *   Foundation initial cards (F0d): -1
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau name (T0Nm): Fan
 *   Tableau initial cards (T0d): -3 (Fill rows with rest of deck)
 *   Empty tableau is filled with (T0f): 1 (Kings only)
 *   Tableau piles (T0n): 16
 *   Tableau suit match rule for building (T0s): 1 (In same suit)
 *   Number of waste piles (W0n): 0
 *   Similar to (like): fan
 *   Allowed draws (ndraw): 1 (1)
 *   Related games (related): boxfan, freefan, ceilingfan, midnightclover
 */
object MidnightClover extends GameRules(
  id = "midnightclover",
  title = "Midnight Clover",
  like = Some("fan"),
  related = Seq("boxfan", "freefan", "ceilingfan", "midnightclover"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/midnight_clover.htm")),
  description = "A ^fan^ variant by Thomas Warfield where a draw is allowed.",
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      initialCards = 4,
      wrapFromKingToAce = true,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      name = "Fan",
      numPiles = 16,
      initialCards = InitialCards.RestOfDeck,
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.Kings
    )
  ),
  special = Some(
    SpecialRules(
      shuffleBeforeRedeal = false,
      drawsAllowed = 1
    )
  ),
  complete = false
)

