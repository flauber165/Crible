package persistence.dao

import java.util.UUID
import com.websudos.phantom.dsl._
import persistence.maps.UserMap
import service.dao.AuthenticationDao
import service.domain.User
import scala.concurrent.Future

private[persistence] abstract class AuthenticationDaoImpl extends UserMap with AuthenticationDao with RootConnector {

  def getUserById(id: UUID): Future[Option[User]] = {
    select.where(_.id eqs id).consistencyLevel_=(ConsistencyLevel.ONE).allowFiltering().one()
  }

  def getUserByEmail(email: String): Future[Option[User]] = {
    select.where(_.email eqs email).consistencyLevel_=(ConsistencyLevel.ONE).allowFiltering().one()
  }

  def updateUserAccessKey(id: UUID, accessKey: String): Future[Boolean] = {
    update.where(_.id eqs id).modify(_.accessKey setTo Option(accessKey)).future().map(_.wasApplied)
  }
}
