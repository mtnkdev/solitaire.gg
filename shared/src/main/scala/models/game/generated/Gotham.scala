// Generated 2015-04-26 for Scalataire.
package models.game.generated

import models.game._
import models.game.rules._

object Gotham extends GameRules(
  id = "gotham",
  title = "Gotham",
  description = "An easier variation of ^newyork^ where we build regardless of suit and same-suit stacks can be moved" +
  ".",
  deckOptions = DeckOptions(
    numDecks = 2,
    lowRank = Some(Rank.Unknown)
  ),
  stock = Some(
    StockRules(
      dealTo = StockDealTo.Manually,
      maximumDeals = Some(1)
    )
  ),
  waste = Some(
    WasteRules(
      numPiles = 3
    )
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 8,
      wrapFromKingToAce = true,
      canMoveFrom = FoundationCanMoveFrom.Never,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit,
      emptyFilledWith = TableauFillEmptyWith.Aces,
      mayMoveToEmptyFrom = Seq("Stock", "Waste")
    )
  ),
  complete = false
)

