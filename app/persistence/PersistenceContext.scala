package persistence

import com.websudos.phantom.db.DatabaseImpl
import com.websudos.phantom.dsl._
import persistence.dao.queries._
import scala.concurrent.duration._
import scala.concurrent.Await

private[persistence] class PersistenceContext(override val connector: KeySpaceDef) extends DatabaseImpl(connector) {
  object userQueryDao extends UserQueryDaoImpl with connector.Connector
  object bankQueryDao extends BankQueryDaoImpl with connector.Connector

  //Gerar o banco de dados. remover de produção...
  Await.result(autocreate.future(), 10 seconds)
}