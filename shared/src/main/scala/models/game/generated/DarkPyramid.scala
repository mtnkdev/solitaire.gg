// Generated 2015-04-26 for Scalataire.
package models.game.generated

import models.game._
import models.game.rules._

object DarkPyramid extends GameRules(
  id = "darkpyramid",
  title = "Dark Pyramid",
  description = "A version of ^pyramid^ where the cards are dealt face down.",
  cardRemovalMethod = CardRemovalMethod.RemovePairsAddingToThirteenOrK,
  stock = Some(
    StockRules(
      dealTo = StockDealTo.WasteOrPairManually,
      maximumDeals = Some(3)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      wrapFromKingToAce = true,
      canMoveFrom = FoundationCanMoveFrom.Never,
      offscreen = true,
      autoMoveCards = true
    )
  ),
  pyramids = Seq(
    PyramidRules(
      cardsFaceDown = PyramidFaceDownCards.AllButLastRow,
      rankMatchRuleForBuilding = RankMatchRule.Down,
      rankMatchRuleForMovingStacks = RankMatchRule.Down,
      mayMoveToNonEmptyFrom = Seq("Waste", "Tableau", "Pyramid", "Foundation"),
      mayMoveToEmptyFrom = Seq("Waste", "Tableau", "Pyramid", "Foundation")
    )
  ),
  complete = false
)

