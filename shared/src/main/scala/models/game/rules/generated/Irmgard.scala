// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object Irmgard extends GameRules(
  id = "irmgard",
  title = "Irmgard",
  description = "A variant of ^gypsy^ where you have an extra tableau pile, but you can only fill spaces with kings.",
  deckOptions = DeckOptions(
    numDecks = 2
  ),
  stock = Some(
    StockRules(
      dealTo = StockDealTo.Tableau,
      maximumDeals = Some(1)
    )
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 8,
      wrapFromKingToAce = true,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 9,
      initialCards = InitialCards.Count(3),
      emptyFilledWith = TableauFillEmptyWith.Kings
    )
  ),
  complete = false
)
