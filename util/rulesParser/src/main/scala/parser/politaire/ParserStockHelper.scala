package parser.politaire

import models.game.rules.{ StockCardsDealt, StockDealTo, StockRules }

trait ParserStockHelper { this: GameRulesParser =>
  protected[this] def getStock = {
    val stockEnabled = getInt("Sn") == 1

    getInt("smode") match {
      case 0 => None
      case _ if !stockEnabled => None
      case stockMode =>
        Some(StockRules(
          name = getString("S0Nm"),
          cardsShown = getInt("S0cardsShown"),
          dealTo = getInt("dealto") match {
            case 1 => StockDealTo.Waste
            case 2 => StockDealTo.Tableau
            case 3 => StockDealTo.TableauIfNoneEmpty
            case 4 => StockDealTo.TableauNonEmpty
            case 5 => StockDealTo.Reserve
            case 6 => StockDealTo.Foundation
            case 7 => StockDealTo.Manually
            case 8 => StockDealTo.Never
            case 9 => StockDealTo.WasteOrPairManually
            case 11 => StockDealTo.TableauFirstSet

            case i => throw new IllegalStateException(i.toString)
          },
          maximumDeals = {
            val maxDeals = getInt("maxdeals")
            if (maxDeals == 10 || maxDeals == 0) { None } else { Some(maxDeals) }
          },
          cardsDealt = getInt("dealchunk") match {
            case -1 => StockCardsDealt.FewerEachTime
            case i => StockCardsDealt.Count(i)
          },
          stopAfterPartialDeal = !getBoolean("wrapdeal"),
          createPocketWhenEmpty = getBoolean("millres"),
          galleryMode = stockMode == 2
        ))
    }
  }
}
