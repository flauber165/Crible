package persistence

import scaldi.Module
import persistence.Connector._
import service.queries.dao.BankQueryDao

class PersistenceModule extends Module {
  val context = new PersistenceContext(connector)
  bind [BankQueryDao] to context.bankQueryDao
}