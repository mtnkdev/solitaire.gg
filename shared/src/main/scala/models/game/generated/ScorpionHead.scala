package models.game.generated

import models.game._
import models.game.rules._

// scalastyle:off
object ScorpionHead extends GameRules(
  id = "scorpionhead",
  title = "Scorpion Head",
  description = "A variation of ^scorpion^ with some cells.",
  victoryCondition = VictoryCondition.AllOnFoundation,
  cardRemovalMethod = CardRemovalMethod.BuildSequencesOnFoundation,
  deckOptions = DeckOptions(
    numDecks = 1,
    suits = Seq(Suit.Hearts, Suit.Spades, Suit.Diamonds, Suit.Clubs),
    ranks = Seq(Rank.Two, Rank.Three, Rank.Four, Rank.Five, Rank.Six, Rank.Seven, Rank.Eight, Rank.Nine, Rank.Ten, Rank.Jack, Rank.Queen, Rank.King, Rank.Ace),
    lowRank = Some(Rank.Ace)
  ),
  stock = Some(
    Stock(
      name = "Stock",
      dealTo = StockDealTo.Waste,
      maximumDeals = Some(1),
      cardsDealt = StockCardsDealt.Count(1),
      stopAfterPartialDeal = true,
      createPocketWhenEmpty = false,
      galleryMode = false
    )
  ),
  waste = None,
  foundations = Seq(
    FoundationSet(
      name = "Foundation",
      numPiles = 4,
      lowRank = FoundationLowRank.DeckLowRank,
      initialCards = InitialCards.Count(0),
      suitMatchRule = SuitMatchRule.SameSuit,
      rankMatchRule = RankMatchRule.Up,
      wrapFromKingToAce = true,
      moveCompleteSequencesOnly = true,
      maxCards = -1,
      canMoveFrom = FoundationCanMoveFrom.Never,
      mayMoveToFrom = Seq("Stock", "Pyramid", "Waste", "Pocket", "Reserve", "Cell", "Foundation", "Tableau"),
      offscreen = false,
      autoMoveCards = false,
      autoMoveFrom = Seq("Stock", "Pyramid", "Waste", "Pocket", "Reserve", "Cell", "Foundation", "Tableau")
    )
  ),
  tableaus = Seq(
    TableauSet(
      name = "Tableau",
      numPiles = 7,
      initialCards = InitialCards.Custom,
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      rankMatchRuleForBuilding = RankMatchRule.Down,
      wrapFromKingToAce = false,
      suitMatchRuleForMovingStacks = SuitMatchRule.Any,
      rankMatchRuleForMovingStacks = RankMatchRule.Any,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.Nowhere,
      emptyFilledWith = TableauFillEmptyWith.Kings,
      mayMoveToNonEmptyFrom = Seq("Stock", "Pyramid", "Waste", "Pocket", "Reserve", "Cell", "Foundation", "Tableau"),
      mayMoveToEmptyFrom = Seq("Stock", "Pyramid", "Waste", "Pocket", "Reserve", "Cell", "Foundation", "Tableau"),
      maxCards = 0,
      actionDuringDeal = PileAction.None,
      actionAfterDeal = PileAction.None,
      pilesWithLowCardsAtBottom = 0
    )
  ),
  cells = Some(
    CellSet(

      name = "Cell",

      pluralName = "Cells",

      numPiles = 4,

      canMoveFrom = Seq("Stock", "Pyramid", "Waste", "Pocket", "Reserve", "Cell", "Foundation", "Tableau"),

      initialCards = 0,

      numEphemeral = 0

    )
  ),
  reserves = None,
  pyramids = Nil
)
// scalastyle:on

