package persistence.dao.queries

import com.websudos.phantom.dsl.{ConsistencyLevel, _}
import persistence.maps.UserMap
import service.dao.queries.QueryDao
import service.domain.User
import service.dto.queries.{FilterResultDto, UserFilterDto}
import scala.concurrent.Future
import persistence.dao.QueryExtensions._

private[persistence] abstract class UserQueryDaoImpl extends UserMap with QueryDao[User, UserFilterDto] with RootConnector {
  def filter(dto: UserFilterDto): Future[FilterResultDto[User]] = {
    val query = select

    if (dto.name.nonEmpty) {
      query.where(_.name eqs dto.name.get)
    }

    if (dto.email.nonEmpty) {
      query.where(_.email eqs dto.email.get)
    }

    query.consistencyLevel_=(ConsistencyLevel.ONE).page(dto)
  }
}