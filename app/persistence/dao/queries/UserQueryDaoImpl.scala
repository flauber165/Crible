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
    select.consistencyLevel_=(ConsistencyLevel.ONE).page(dto)
  }
}