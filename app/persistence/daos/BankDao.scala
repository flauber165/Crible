package persistence.daos

import com.websudos.phantom.dsl._
import domain.Bank
import persistence.maps.BankMap

import scala.concurrent.Future

abstract class BankDao extends BankMap with RootConnector {
  def getAll(): Future[List[Bank]] = {
    select.consistencyLevel_=(ConsistencyLevel.ONE).fetch()
  }
}
