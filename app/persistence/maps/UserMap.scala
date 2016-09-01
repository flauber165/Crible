package persistence.maps

import java.util.UUID

import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import service.domain.RoleKind.RoleKind
import service.domain.{RoleKind, User}

private[persistence] class UserMap extends CassandraTable[UserMap, User] {

  override def tableName: String = "user"

  object id extends UUIDColumn(this) with PartitionKey[UUID] { override lazy val name = "id" }
  object name extends StringColumn(this) with Index[String]
  object email extends StringColumn(this) with Index[String]
  object password extends StringColumn(this)
  object accessKey extends OptionalStringColumn(this)
  object role extends EnumColumn(this, RoleKind) with Index[RoleKind]

  implicit override def fromRow(r: Row): User = User(id(r), name(r), email(r), password(r), accessKey(r), role(r))
}

