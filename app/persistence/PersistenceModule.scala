package persistence

import scaldi.Module
import persistence.Connector._
import service.dao.queries.BankQueryDao

class PersistenceModule extends Module {
  val context = new PersistenceContext(connector)
  bind [BankQueryDao] to context.bankQueryDao
}