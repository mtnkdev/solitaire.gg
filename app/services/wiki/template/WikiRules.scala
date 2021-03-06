package services.wiki.template

import models.rules.{GameRules, GameRulesSet}
import services.wiki.WikiService.messages

class WikiRules(r: GameRules) {
  private[this] val ret = new StringBuilder()
  private[this] def l(s: String = ""): Unit = ret.append(s + "\n")

  private[this] def rulesLink(id: String, name: String) = {
    s"[$name]($id)"
  }

  def getTemplate = {
    l("# " + r.title)
    l()
    processDescription(r)
    l()
    processAka()
    processObjective()
    processDeck()
    processLayout()
    processSimilar()
    processRelated()
    processLinks()

    ret.toString
  }

  private[this] val descriptionLinkPattern = """\^([a-z0-9]+)\^""".r

  private[this] def processDescription(rules: GameRules, link: Boolean = true) = {
    val desc = messages(s"rules.${rules.id}.description")
    val links = descriptionLinkPattern.findAllIn(desc).matchData.map(_.group(1))
    val linked = links.foldLeft(desc) { (desc, id) =>
      val rules = GameRulesSet.allByIdWithAliases(id)
      if (link) {
        desc.replaceAllLiterally(s"^$id^", s"""[${rules.title}](${rules.id})""")
      } else {
        desc.replaceAllLiterally(s"^$id^", rules.title)
      }
    }
    l(linked)
  }

  private[this] def withTitle(title: String)(f: => Unit) = {
    l("## " + messages(title))
    l()
    f
    l()
  }

  private[this] def processAka() = if (r.aka.nonEmpty) {
    withTitle("help.also.known.as")(r.aka.toSeq.foreach { aka =>
      l(rulesLink(aka._1, aka._2))
    })
  }

  private[this] def processObjective() = withTitle("help.objective")(l(WikiObjective.objective(r)))

  private[this] def processDeck() = withTitle("help.deck")(WikiDeckOptions.deck(r.deckOptions).foreach(l))

  private[this] def processLayout() = withTitle("help.layout")(WikiLayout.layout(r).foreach { pileSet =>
    l("### " + pileSet._1)
    l()
    pileSet._2.foreach { statement =>
      l(statement)
    }
    l()
  })

  private[this] def processSimilar() = r.like.foreach { likeId =>
    withTitle("help.original.game") {
      val like = models.rules.GameRulesSet.allByIdWithAliases(likeId)
      l(rulesLink(like.id, like.title))
      processDescription(like, link = false)
    }
  }

  private[this] def processRelated() = {
    val rels = GameRulesSet.allByIdWithAliases.filter(x => r.related.contains(x._2.id)).toSeq
    if (rels.nonEmpty) {
      withTitle("help.related.games")(rels.foreach { rel =>
        l(rulesLink(rel._1, rel._2.title))
        processDescription(rel._2, link = false)
        l()
      })
    }
  }

  private[this] def processLinks() = if (r.links.nonEmpty) {
    withTitle("help.web.resources")(r.links.foreach { link =>
      l(s"[${link.title}](${link.url})")
      l()
    })
  }
}
