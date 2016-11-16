package service.domain

import org.mongodb.scala.bson.BsonDocument

object Portfolio extends BsonDocument{
  type Portfolio = Portfolio.type
  val total: String = null
  val toExpired: String  = null
  val expired: String = null
}