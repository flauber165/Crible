package service.queries

import service.domain.Bank
import service.dao.queries.BankQueryDao

import scala.concurrent.Future

class BankQueryService(bankQueryDao: BankQueryDao) {
  def getAll(): Future[List[Bank]] = {
    bankQueryDao.getAll()
  }
}
