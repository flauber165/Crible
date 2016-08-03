package service.queries

import domain.Bank
import service.queries.dao.BankQueryDao
import scala.concurrent.Future

class BankQuery(bankQueryDao: BankQueryDao) {
  def getAll(): Future[List[Bank]] = {
    bankQueryDao.getAll()
  }
}
