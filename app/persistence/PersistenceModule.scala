package persistence

import com.google.inject.{AbstractModule, TypeLiteral}
import persistence.Connector._
import service.dao.AuthenticationDao
import service.dao.queries._
import service.domain.User
import service.dto.queries.UserFilterDto

class PersistenceModule extends AbstractModule {
  val context = new PersistenceContext(connector)
  def configure() = {
    bind(classOf[AuthenticationDao]).toInstance(context.authenticationDao)
    bind(new TypeLiteral[QueryDao[User, UserFilterDto]] {}).toInstance(context.userQueryDao)
    bind(classOf[BankQueryDao]).toInstance(context.bankQueryDao)
  }
}