package persistence

import com.typesafe.config.ConfigFactory
import org.mongodb.scala.MongoClient

private[persistence] object Connector {
  private val config = ConfigFactory.load()
  private val mongoClient = MongoClient(config.getString("db"))
  val database = mongoClient.getDatabase("crible")
}