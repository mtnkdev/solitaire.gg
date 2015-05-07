// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Foundation initial cards (F0d): -1
 *   Foundation low rank (F1b): 22 (Deck's high card)
 *   Foundation rank match rule (F1r): 0x0020
 *   Foundation Sets (Fn): 2
 *   Tableau initial cards (T0d): 4 (4 cards)
 *   Empty tableau is filled with (T0f): 5 (No card)
 *   Tableau piles (T0n): 12
 *   Tableau rank match rule for building (T0r): 0x0020|0x0080
 *   Tableau suit match rule for building (T0s): 1 (In same suit)
 */
object Bisley extends GameRules(
  id = "bisley",
  title = "Bisley",
  links = Seq(
    Link("Wikipedia", "en.wikipedia.org/wiki/Bisley_(solitaire)"),
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/bisley.htm"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/bisley.html"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/bisley.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/bisley.htm"),
    Link("Solitaire Till Dawn", "www.semicolon.com/Solitaire/Rules/Bisley.html"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/bisley.php"),
    Link("John Welford on HobbyHub", "www.hobbyhub360.com/index.php/how-to-play-bisley-solitaire-25748/")
  ),
  description = "A game of building up and down on the tableau.",
  stock = Some(
    StockRules(
      maximumDeals = Some(1)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      initialCards = 4,
      wrapFromKingToAce = true,
      autoMoveCards = true
    ),
    FoundationRules(
      setNumber = 1,
      numPiles = 4,
      lowRank = FoundationLowRank.DeckHighRank,
      rankMatchRule = RankMatchRule.Down,
      wrapFromKingToAce = true,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 12,
      initialCards = InitialCards.Count(4),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      rankMatchRuleForBuilding = RankMatchRule.UpOrDown,
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  ),
  complete = false
)

