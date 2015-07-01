package models.database.queries.history

import java.util.UUID

import models.database.queries.BaseQueries
import models.database.{ Query, Row, Statement }
import models.history.GameHistory
import org.joda.time.LocalDateTime
import utils.DateUtils

object GameHistoryQueries extends BaseQueries[GameHistory] {
  override protected val tableName = "games"
  override protected val columns = Seq("id", "seed", "rules", "status", "player", "moves", "undos", "redos", "created", "completed")
  override protected val searchColumns = Seq("id::text", "seed::text", "rules", "status", "player::text")

  val getById = GetById
  val insert = Insert
  def searchCount(q: String, groupBy: Option[String] = None) = new SearchCount(q, groupBy)
  val search = Search
  val removeById = RemoveById

  case class UpdateGameHistory(id: UUID, status: String, moves: Int, undos: Int, redos: Int, completed: Option[LocalDateTime]) extends Statement {
    override val sql = updateSql(Seq("status", "moves", "undos", "redos", "completed"))
    override val values = Seq[Any](status, moves, undos, redos, completed.map(DateUtils.toSqlTimestamp), id)
  }

  case class GetGameHistoryIdsForUser(userId: UUID) extends Query[List[UUID]] {
    override val sql = s"select id from $tableName where player = ?"
    override val values = Seq(userId)
    override def reduce(rows: Iterator[Row]) = rows.map(_.as[String]("id")).map(UUID.fromString).toList
  }

  def getGameHistoryCountForUser(userId: UUID) = new Count(s"select count(*) as c from $tableName where player = ?", Seq(userId))

  override protected def fromRow(row: Row) = {
    val id = UUID.fromString(row.as[String]("id"))
    val seed = row.as[Int]("seed")
    val rules = row.as[String]("rules")
    val status = row.as[String]("status")
    val player = UUID.fromString(row.as[String]("player"))
    val moves = row.as[Int]("moves")
    val undos = row.as[Int]("undos")
    val redos = row.as[Int]("redos")
    val created = row.as[LocalDateTime]("created")
    val complete = row.asOpt[LocalDateTime]("completed")
    GameHistory(id, seed, rules, status, player, moves, undos, redos, created, complete)
  }

  override protected def toDataSeq(gh: GameHistory) = Seq[Any](
    gh.id, gh.seed, gh.rules, gh.status, gh.player, gh.moves, gh.undos, gh.redos, gh.created, gh.completed
  )
}
