package persistence

import com.google.inject.{AbstractModule, TypeLiteral}
import org.mongodb.scala.MongoDatabase
import persistence.dao.AuthenticationDaoImpl
import persistence.dao.queries.{BankQueryDaoImpl, UserQueryDaoImpl}
import service.dao.AuthenticationDao
import service.dao.queries._
import service.domain.User
import service.dto.queries.UserFilterDto

class PersistenceModule extends AbstractModule {
  def configure() = {
    bind(classOf[MongoDatabase]).toInstance(Connector.mongoClient.getDatabase("crible"))
    bind(classOf[AuthenticationDao]).to(classOf[AuthenticationDaoImpl])
    bind(new TypeLiteral[QueryDao[User, UserFilterDto]] {}).to(classOf[UserQueryDaoImpl])
    bind(classOf[BankQueryDao]).to(classOf[BankQueryDaoImpl])
  }
}