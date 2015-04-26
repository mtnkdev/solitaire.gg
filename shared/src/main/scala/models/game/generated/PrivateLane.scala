// Generated 2015-04-26 for Scalataire.
package models.game.generated

import models.game._
import models.game.rules._

object PrivateLane extends GameRules(
  id = "privatelane",
  title = "Private Lane",
  description = "A variation of ^beleagueredcastle^ with two ^freecell^-style cells added.",
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      wrapFromKingToAce = true,
      canMoveFrom = FoundationCanMoveFrom.Never,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.RestOfDeck,
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = TableauFillEmptyWith.Aces
    )
  ),
  cells = Some(
    CellRules(

      numPiles = 2
    )
  ),
  complete = false
)

