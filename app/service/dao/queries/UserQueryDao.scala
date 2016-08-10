package service.dao.queries

import java.util.UUID

import service.domain.User

import scala.concurrent.Future

trait UserQueryDao {
  def getUserByIdAndPassword(id: UUID, password: String): Future[Option[User]]
  def getUserByEmailAndPassword(email: String, password: String): Future[Option[User]]
}
