package persistence

import com.google.inject.name.Names
import com.google.inject.{AbstractModule, Provider, TypeLiteral}
import org.mongodb.scala._

class PersistenceModule extends AbstractModule {
  def configure() = {
    bindCollection("user")
    bindCollection("dashboard")
  }

  private def bindCollection(alias: String)= {
    bind(new TypeLiteral[MongoCollection[Document]] {}).annotatedWith(Names.named(alias)).toProvider(new Provider[MongoCollection[Document]] {
      override def get(): MongoCollection[Document] = Connector.database.getCollection(alias)
    })
  }
}