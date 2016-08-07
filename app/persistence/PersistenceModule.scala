package persistence

import scaldi.Module
import persistence.Connector._
import service.dao.queries._

class PersistenceModule extends Module {
  val context = new PersistenceContext(connector)
  bind [UserQueryDao] toProvider context.userQueryDao
  bind [BankQueryDao] to context.bankQueryDao
}