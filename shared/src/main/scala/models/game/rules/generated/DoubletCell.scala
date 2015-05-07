// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Number of cells (C0n): 4
 *   Foundation low rank (F0b): 20 (Any Card)
 *   Maximum cards for foundation (F0m): 0
 *   Enable stock (Sn): 0 (No stock)
 *   Auto-fill an empty tableau from (T0af): 0 (Nowhere)
 *   Tableau initial cards (T0d): -3 (Fill rows with rest of deck)
 *   Empty tableau is filled with (T0f): 5 (No card)
 *   Tableau piles (T0n): 8
 *   Tableau suit match rule for building (T0s): 0 (May not build)
 *   Number of waste piles (W0n): 0
 *   Deal cards from stock (dealto): 8 (Never)
 *   Left mouse interface function (leftfunc): 0x1
 *   Similar to (like): simplepairs
 *   Card removal method (pairs): 2 (Remove pairs of same rank and color)
 *   Related games (related): crisscross, eighteens, straightfifteens, blockten, patientpairs, doubletcell, do...
 */
object DoubletCell extends GameRules(
  id = "doubletcell",
  title = "Doublet Cell",
  like = Some("simplepairs"),
  related = Seq("crisscross", "eighteens", "straightfifteens", "blockten", "patientpairs", "doubletcell", "doublets"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/doublet_cell.htm")),
  description = "A combination between ^doublets^ and ^freecell^.",
  cardRemovalMethod = CardRemovalMethod.RemovePairsOfSameRankAndColor,
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      lowRank = FoundationLowRank.AnyCard,
      wrapFromKingToAce = true,
      maxCards = 0,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.RestOfDeck,
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.None,
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  ),
  cells = Some(CellRules()),
  complete = false
)

