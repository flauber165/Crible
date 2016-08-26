package service.dao

import java.util.UUID

import service.domain.User

import scala.concurrent.Future

trait AuthenticationDao {
  def getUserById(id: UUID): Future[Option[User]]
  def getUserByEmail(email: String): Future[Option[User]]
  def updateUserAccessKey(id: UUID, accessKey: String): Future[Boolean]
}
