package persistence.dao.maps

import org.mongodb.scala.Document
import service.domain.{RoleKind, User}

object UserMap extends Map[User] {
  def to(item: User): Document = {
    Document("accessKey" -> item.accessKey,
      "email" -> item.email,
      "name" -> item.name,
      "role" -> item.role.id,
      "password" -> item.password)
  }

  def from(document: Document): User = {
    User(document.get("_id").get.asObjectId.getValue.toHexString,
      document.get("name").get.asString.getValue,
      document.get("email").get.asString.getValue,
      document.get("password").get.asString.getValue,
      if (document.get("accessKey").get.isNull) None else Some(document.get("accessKey").get.asString.getValue),
      RoleKind(document.get("role").get.asInt32.getValue))
  }
}