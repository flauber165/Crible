package persistence

import com.typesafe.config.ConfigFactory
import org.mongodb.scala.MongoClient

private[persistence] object Connector {
  val config = ConfigFactory.load()
  val mongoClient = MongoClient(config.getString("db"))
}