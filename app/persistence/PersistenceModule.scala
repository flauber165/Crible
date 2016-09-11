package persistence

import com.google.inject.name.Names
import com.google.inject.{AbstractModule, Provider, TypeLiteral}
import org.mongodb.scala._
import persistence.dao.AuthenticationDaoImpl
import persistence.dao.queries.{BankQueryDaoImpl, UserQueryDaoImpl}
import service.dao.AuthenticationDao
import service.dao.queries._
import service.domain.User
import service.dto.queries.UserFilterDto

class PersistenceModule extends AbstractModule {
  def configure() = {
    bindCollection("user")
    bind(classOf[AuthenticationDao]).to(classOf[AuthenticationDaoImpl])
    bind(new TypeLiteral[QueryDao[User, UserFilterDto]] {}).to(classOf[UserQueryDaoImpl])
    bind(classOf[BankQueryDao]).to(classOf[BankQueryDaoImpl])
  }

  private def bindCollection(alias: String)= {
    bind(new TypeLiteral[MongoCollection[Document]] {}).annotatedWith(Names.named(alias)).toProvider(new Provider[MongoCollection[Document]] {
      override def get(): MongoCollection[Document] = Connector.database.getCollection(alias)
    })
  }
}