package service

import com.google.inject.Inject
import com.google.inject.name.Named
import org.mindrot.jbcrypt.BCrypt
import org.mongodb.scala.bson.BsonObjectId
import org.mongodb.scala.model.Filters._
import org.mongodb.scala.model.Updates._
import org.mongodb.scala.{MongoCollection, _}
import persistence.dao.maps.UserMap
import play.api.libs.concurrent.Execution.Implicits.defaultContext

import scala.concurrent.{Future, Promise}

/**
  * Created by italosantana on 12/11/16.
  */
class UpdatePasswordService @Inject()(@Named("user") collection: MongoCollection[Document]){

  def updatePassword(id: String, newPassword: String): Future[Boolean] = {
    val promise = Promise[Boolean]

    try{

      if(id.isEmpty || newPassword.isEmpty){
        throw new Throwable
      }

      collection.find(equal("_id", BsonObjectId(id))).first.toFuture.onComplete( r => {
        try {

          if (r.get.isEmpty) {
            throw new Throwable
          }

          val user = UserMap.from(r.get.head)

          collection.updateOne(equal("_id", BsonObjectId(user.id)), set("password", BCrypt.hashpw(newPassword, BCrypt.gensalt))).head.onComplete(b => {
            promise.success(true)
          })

        }
        catch {
          case  e: Throwable => promise.failure(e)
        }
      })

    }
    catch{
      case  e: Throwable => promise.failure(e)
    }

    promise.future
  }

}
