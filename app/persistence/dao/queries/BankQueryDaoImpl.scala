package persistence.dao.queries

//import com.websudos.phantom.dsl._
import service.domain.Bank
//import persistence.maps.BankMap
import service.dao.queries.BankQueryDao

import scala.concurrent.Future

private[persistence] class BankQueryDaoImpl extends BankQueryDao {
  def getAll(): Future[List[Bank]] = {
    null
  }
}
