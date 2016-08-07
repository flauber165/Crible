package service.dao.queries

import service.domain.User

import scala.concurrent.Future

trait UserQueryDao {
  def getUserByEmailAndPassword(email: String, password: String): Future[Option[User]]
}
