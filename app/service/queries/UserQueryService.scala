package service.queries

import com.google.inject.Inject
import service.dao.queries.{BankQueryDao, QueryDao}
import service.domain.User
import service.dto.queries.{FilterResultDto, UserFilterDto}

import scala.concurrent.Future

class UserQueryService @Inject()(userQueryDao: QueryDao[User, UserFilterDto]) {
  def filter(dto: UserFilterDto): Future[FilterResultDto[User]] = {
    userQueryDao.filter(dto)
  }
}