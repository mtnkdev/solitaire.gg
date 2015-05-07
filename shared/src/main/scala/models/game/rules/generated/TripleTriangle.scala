// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Tableau initial cards (T0d): -1 (1 to n cards)
 *   Empty tableau is filled with (T0f): 1 (Kings only)
 *   Tableau piles (T0n): 13
 *   Tableau suit match rule for building (T0s): 4 (In alternating colors)
 *   Tableau suit match rule for moving stacks (T0ts): 4 (In alternating colors)
 *   Number of waste piles (W0n): 0
 *   Deal cards from stock (dealto): 2 (To all tableau piles)
 *   Similar to (like): eternaltriangle
 *   Number of decks (ndecks): 3 (3 decks)
 *   Related games (related): hypotenuse, tripletriangle
 */
object TripleTriangle extends GameRules(
  id = "tripletriangle",
  title = "Triple Triangle",
  like = Some("eternaltriangle"),
  related = Seq("hypotenuse", "tripletriangle"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/triple_triangle.htm")),
  description = "A three-deck ^eternaltriangle^ variation by Thomas Warfield.",
  deckOptions = DeckOptions(
    numDecks = 3
  ),
  stock = Some(
    StockRules(
      dealTo = StockDealTo.Tableau,
      maximumDeals = Some(1)
    )
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 12,
      wrapFromKingToAce = true,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 13,
      cardsFaceDown = TableauFaceDownCards.Count(0),
      emptyFilledWith = FillEmptyWith.Kings
    )
  ),
  complete = false
)

