package service.queries.dao

import domain.Bank

import scala.concurrent.Future

trait BankQueryDao {
  def getAll(): Future[List[Bank]]
}
