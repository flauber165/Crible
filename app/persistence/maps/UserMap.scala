package persistence.maps

import java.util.UUID

import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import service.domain.User

private[persistence] class UserMap extends CassandraTable[UserMap, User] {

  override def tableName: String = "user"

  object id extends UUIDColumn(this) with ClusteringOrder[UUID] { override lazy val name = "id" }
  object name extends StringColumn(this)
  object email extends StringColumn(this) with PartitionKey[String]
  object password extends StringColumn(this) with PartitionKey[String]

  override def fromRow(r: Row): User = User(id(r), name(r), email(r), password(r))
}

