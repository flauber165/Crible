package service

import java.util.UUID

import org.mongodb.scala.{MongoCollection, _}

import scala.concurrent.{Future, Promise}
import com.google.inject.Inject
import com.google.inject.name.Named
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import org.mongodb.scala._
import org.mongodb.scala.bson.BsonObjectId
import org.mongodb.scala.model.Filters._
import org.mongodb.scala.model.Updates._
import persistence.dao.maps.UserMap

import scala.Some



/**
  * Created by italosantana on 10/11/16.
  */
class ResetPasswordService @Inject()(@Named("user") collection: MongoCollection[Document]) {


def resetPassword(id: Option[String], token: Option[String]): Future[Boolean] = {
  val promise = Promise[Boolean]

  try{

    collection.find(equal("_id", BsonObjectId(id.get))).first.toFuture.onComplete(r => {
      try {

        if (r.get.isEmpty) {
          throw new Throwable
        }

        val user = UserMap.from(r.get.head)

        if (!user.accessKey.equals(token)) {
          throw new Throwable
        }

        val accessKey = UUID.randomUUID.toString

        collection.updateOne(equal("_id", BsonObjectId(user.id)), set("accessKey", accessKey)).head.onComplete(b => {
          promise.success(true)
        })

      }
      catch {
        case e: Throwable => promise.failure(e)
      }
    })
  }
  catch {
    case e: Throwable => promise.failure(e)
  }
  promise.future
}
}