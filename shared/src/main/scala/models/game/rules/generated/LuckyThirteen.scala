// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object LuckyThirteen extends GameRules(
  id = "luckythirteen",
  title = "Lucky Thirteen",
  description = "A rarely-winnable game with simple \"rules\": build down regardless of suit, no stack moves.",
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
      numPiles = 13,
      initialCards = InitialCards.Count(4),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  ),
  complete = false
)
