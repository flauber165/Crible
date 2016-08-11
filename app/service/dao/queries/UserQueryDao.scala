package service.dao.queries

import java.util.UUID

import service.domain.RoleKind._
import service.domain.User

import scala.concurrent.Future

trait UserQueryDao {
  def getUserByIdAndPasswordAndRole(id: UUID, password: String, role: RoleKind): Future[Option[User]]
  def getUserByEmail(email: String): Future[Option[User]]
}
