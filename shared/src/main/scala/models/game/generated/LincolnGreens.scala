// Generated 2015-04-26 for Scalataire.
package models.game.generated

import models.game._
import models.game.rules._

object LincolnGreens extends GameRules(
  id = "lincolngreens",
  title = "Lincoln Greens",
  description = "A four-deck variation of ^puttputt^, or a version of ^panthercreek^ that allows wrapping.",
  victoryCondition = VictoryCondition.AllOnFoundationOrStock,
  deckOptions = DeckOptions(
    numDecks = 4
  ),
  stock = Some(
    StockRules(
      dealTo = StockDealTo.Foundation,
      maximumDeals = Some(1)
    )
  ),
  foundations = Seq(
    FoundationRules(
      lowRank = FoundationLowRank.AnyCard,
      initialCards = InitialCards.Count(1),
      suitMatchRule = SuitMatchRule.Any,
      rankMatchRule = RankMatchRule.UpOrDown,
      wrapFromKingToAce = true,
      maxCards = 0,
      canMoveFrom = FoundationCanMoveFrom.Never,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 12,
      initialCards = InitialCards.Count(12),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.None,
      rankMatchRuleForBuilding = RankMatchRule.None,
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  ),
  complete = false
)

