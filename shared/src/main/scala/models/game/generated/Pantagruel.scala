// Generated 2015-04-26 for Scalataire.
package models.game.generated

import models.game._
import models.game.rules._

object Pantagruel extends GameRules(
  id = "pantagruel",
  title = "Pantagruel",
  description = "This two-deck ^klondike^ variant is more difficult than ^gargantua^, but is still pretty easy.",
  deckOptions = DeckOptions(
    numDecks = 2
  ),
  stock = Some(
    StockRules(
      maximumDeals = Some(1)
    )
  ),
  waste = Some(WasteRules()),
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
      numPiles = 9,
      emptyFilledWith = TableauFillEmptyWith.Aces
    )
  ),
  complete = false
)

