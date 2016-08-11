package persistence.dao.queries

import java.util.UUID

import com.websudos.phantom.dsl._
import persistence.maps.UserMap
import service.dao.queries.UserQueryDao
import service.domain.RoleKind.RoleKind
import service.domain.{RoleKind, User}

import scala.concurrent.Future

private[persistence] abstract class UserQueryDaoImpl extends UserMap with UserQueryDao with RootConnector {

  def getUserByIdAndPasswordAndRole(id: UUID, password: String, role: RoleKind): Future[Option[User]] = {
    var query = select.where(_.id eqs id).and(_.password eqs password)

    if(role != null) {
      role match {
        case RoleKind.Administrator => query = query.and(_.role in List(RoleKind.Administrator))
        case RoleKind.Director => query = query.and(_.role in List(RoleKind.Administrator, RoleKind.Director))
        case RoleKind.GeneralManager => query = query.and(_.role in List(RoleKind.Administrator, RoleKind.Director, RoleKind.GeneralManager))
        case RoleKind.AccountManager => query = query.and(_.role in List(RoleKind.Administrator, RoleKind.Director, RoleKind.GeneralManager, RoleKind.AccountManager))
      }
    }

    query.consistencyLevel_=(ConsistencyLevel.ONE).allowFiltering().one()
  }

  def getUserByEmail(email: String): Future[Option[User]] = {
    select.where(_.email eqs email).consistencyLevel_=(ConsistencyLevel.ONE).allowFiltering().one()
  }
}
