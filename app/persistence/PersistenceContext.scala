package persistence

import com.websudos.phantom.db.DatabaseImpl
import com.websudos.phantom.dsl._
import persistence.dao.queries._

private[persistence] class PersistenceContext(override val connector: KeySpaceDef) extends DatabaseImpl(connector) {
  object userQueryDao extends UserQueryDaoImpl with connector.Connector
  object bankQueryDao extends BankQueryDaoImpl with connector.Connector
}