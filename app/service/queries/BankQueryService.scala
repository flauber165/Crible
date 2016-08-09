package service.queries

import com.google.inject.Inject
import service.domain.Bank
import service.dao.queries.BankQueryDao

import scala.concurrent.Future

class BankQueryService @Inject()(bankQueryDao: BankQueryDao) {
  def getAll(): Future[List[Bank]] = {
    bankQueryDao.getAll()
  }
}
