package persistence

import com.websudos.phantom.db.DatabaseImpl
import com.websudos.phantom.dsl._
import persistence.daos.BankDao
import persistence.Connector._

class BasicDatabase(override val connector: KeySpaceDef) extends DatabaseImpl(connector) {
  object bankDao extends BankDao with connector.Connector
}

object ProductionDatabase extends BasicDatabase(connector)