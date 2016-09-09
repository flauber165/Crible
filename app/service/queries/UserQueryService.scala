package service.queries

import com.google.inject.Inject
import service.dao.queries.QueryDao
import service.domain.User
import service.dto.queries.UserFilterDto
import scala.concurrent.Future

class UserQueryService @Inject()(userQueryDao: QueryDao[User, UserFilterDto]) {
  def filter(dto: UserFilterDto): Future[Seq[User]] = {
    userQueryDao.filter(dto)
  }
}