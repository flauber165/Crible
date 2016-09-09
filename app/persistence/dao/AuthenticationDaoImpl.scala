package persistence.dao

import org.mongodb.scala.model.Filters._
import com.google.inject.Inject
import org.mongodb.scala.bson.BsonObjectId
import org.mongodb.scala.model.Updates._
import org.mongodb.scala.MongoDatabase
import persistence.dao.maps.UserMap
import service.dao.AuthenticationDao
import service.domain.User
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import scala.concurrent.Future

private[persistence] class AuthenticationDaoImpl @Inject()(database: MongoDatabase) extends AuthenticationDao {

  def getUserById(id: String): Future[Option[User]] = {
    database.getCollection("user").find(equal("_id", BsonObjectId(id))).first.toFuture.map(d => {
      if(d.nonEmpty) {
        Option(UserMap.from(d.head))
      }
      else {
        Option(null)
      }
    })
  }

  def getUserByEmail(email: String): Future[Option[User]] = {
    database.getCollection("user").find(equal("email", email)).first.toFuture.map(d => {
        if(d.nonEmpty) {
          Option(UserMap.from(d.head))
        }
        else {
          Option(null)
        }
      })
  }

  def updateUserAccessKey(id: String, accessKey: String): Future[Boolean] = {
    database.getCollection("user").updateOne(equal("_id", BsonObjectId(id)), set("accessKey", accessKey)).head
      .map(d => d.getModifiedCount > 0)
  }
}
