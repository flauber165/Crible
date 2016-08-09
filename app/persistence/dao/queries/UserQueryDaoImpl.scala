package persistence.dao.queries

import com.websudos.phantom.dsl._
import persistence.maps.UserMap
import service.dao.queries.UserQueryDao
import service.domain.User

import scala.concurrent.Future

private[persistence] abstract class UserQueryDaoImpl extends UserMap with UserQueryDao with RootConnector {
  def getUserByEmailAndPassword(email: String, password: String): Future[Option[User]] = {
    select.where(_.email eqs email).and(_.password eqs password).consistencyLevel_=(ConsistencyLevel.ONE).allowFiltering().one()
  }
}
