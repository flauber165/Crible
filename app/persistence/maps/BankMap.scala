package persistence.maps

import java.util.UUID

import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import domain.Bank

class BankMap extends CassandraTable[BankMap, Bank] {

  override def tableName: String = "bank"

  object id extends UUIDColumn(this) with ClusteringOrder[UUID] { override lazy val name = "id" }
  object name extends StringColumn(this)

  override def fromRow(r: Row): Bank = Bank(id(r), name(r))
}
