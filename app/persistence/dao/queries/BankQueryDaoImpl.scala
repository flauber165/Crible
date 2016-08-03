package persistence.dao.queries

import com.websudos.phantom.dsl._
import domain.Bank
import persistence.maps.BankMap
import service.queries.dao.BankQueryDao

import scala.concurrent.Future

private[persistence] abstract class BankQueryDaoImpl extends BankMap with BankQueryDao with RootConnector {
  def getAll(): Future[List[Bank]] = {
    select.consistencyLevel_=(ConsistencyLevel.ONE).fetch()
  }
}