package service

import java.util.{Base64, UUID}
import com.google.inject.Inject
import org.mindrot.jbcrypt.BCrypt
import service.dto.{EnterDto, EnterResultDto}
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import service.domain.RoleKind.RoleKind
import service.domain.{RoleKind, User}
import com.wix.accord._
import dsl._
import scala.concurrent.{Future, Promise}
import ServiceValidator.validateAndThrow
import com.google.inject.name.Named
import org.mongodb.scala._
import org.mongodb.scala.bson.BsonObjectId
import org.mongodb.scala.model.Filters._
import org.mongodb.scala.model.Updates._
import persistence.dao.maps.UserMap
import service.exceptions.{AuthenticationException, UnauthorizedException}
import service.FilterExtensions._
import scala.collection.mutable.ListBuffer

class AuthenticationService @Inject()(@Named("user") collection: MongoCollection[Document]) {

  val encoder = Base64.getEncoder
  val decoder = Base64.getDecoder

  implicit val enterDtoValidator = validator[EnterDto] { e =>
    e.email as "emailEmptyField" is notEmpty
    e.password as "passwordEmptyField" is notEmpty
  }

  def check(authorization: String, role: RoleKind): Future[User] = {
    val promise = Promise[User]
    try {
      val array = new String(decoder.decode(authorization.substring(6))).split(":")
      if(array.length == 2) {

        val roles = ListBuffer[RoleKind]()

        role match {
          case RoleKind.Administrator =>
            roles += RoleKind.Administrator
          case RoleKind.Director =>
            roles += RoleKind.Administrator
            roles += RoleKind.Director
          case RoleKind.GeneralManager =>
            roles += RoleKind.Administrator
            roles += RoleKind.Director
            roles += RoleKind.GeneralManager
          case RoleKind.AccountManager =>
            roles += RoleKind.Administrator
            roles += RoleKind.Director
            roles += RoleKind.GeneralManager
            roles += RoleKind.AccountManager
        }

        collection.find(and(equal("_id", BsonObjectId(array(0))), equal("accessKey", array(1)), roles.map(_.id).in("role")))
          .first.toFuture.onComplete(r => {
            if(r.get.nonEmpty) {
              promise.success(UserMap.from(r.get.head))
            }
            else {
              promise.failure(new UnauthorizedException())
            }
          })
      }
      else {
        promise.failure(new UnauthorizedException())
      }
    }
    catch {
      case e: Throwable => promise.failure(new UnauthorizedException())
    }

    promise.future
  }

  def enter(enterDto: EnterDto): Future[EnterResultDto] = {
    val promise = Promise[EnterResultDto]
    try {
      //Gerar senha Para teste... remover em produção...
      println(BCrypt.hashpw(enterDto.password, BCrypt.gensalt))

      validateAndThrow(enterDto)

      collection.find(equal("email", enterDto.email)).first.toFuture.onComplete(r => {
        try {
          if (r.get.isEmpty) {
            throw new AuthenticationException()
          }

          val user = UserMap.from(r.get.head)

          if (!BCrypt.checkpw(enterDto.password, user.password)) {
            throw new AuthenticationException()
          }

          val accessKey = UUID.randomUUID.toString

          collection.updateOne(equal("_id", BsonObjectId(user.id)), set("accessKey", accessKey)).head.onComplete(b => {
            promise.success(EnterResultDto(encoder.encodeToString(s"${user.id}:${accessKey}".getBytes), user.name))
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
