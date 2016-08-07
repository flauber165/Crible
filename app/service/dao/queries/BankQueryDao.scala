package service.dao.queries

import service.domain.Bank

import scala.concurrent.Future

trait BankQueryDao {
  def getAll(): Future[List[Bank]]
}
