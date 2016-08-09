package persistence

import com.google.inject.AbstractModule
import persistence.Connector._
import service.dao.queries._

class PersistenceModule extends AbstractModule {
  val context = new PersistenceContext(connector)
  def configure() = {
    bind(classOf[UserQueryDao]).toInstance(context.userQueryDao)
    bind(classOf[BankQueryDao]).toInstance(context.bankQueryDao)
  }
}