package persistence

import com.websudos.phantom.db.DatabaseImpl
import com.websudos.phantom.dsl._
import persistence.dao.queries.BankQueryDaoImpl

private[persistence] class PersistenceContext(override val connector: KeySpaceDef) extends DatabaseImpl(connector) {
  object bankQueryDao extends BankQueryDaoImpl with connector.Connector
}