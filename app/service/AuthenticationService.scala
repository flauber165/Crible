package service

import java.util.{Base64, UUID}

import com.google.inject.Inject
import org.mindrot.jbcrypt.BCrypt
import service.dao.queries.UserQueryDao
import service.dto.{EnterDto, EnterResultDto}
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import service.domain.RoleKind.RoleKind
import service.domain.{RoleKind, User}
import com.wix.accord._
import dsl._

import scala.concurrent.{Future, Promise}
import ServiceValidator.validateAndThrow
import service.exceptions.{AuthenticationException, I18nException, UnauthorizedException}

class AuthenticationService @Inject()(userQueryDao: UserQueryDao) {

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
        userQueryDao.getUserById(UUID.fromString(array(0))).onComplete(r => {
          if(r.get.nonEmpty) {
            val user = r.get.get
            if(user.accessKey.get == array(1)) {

              var isAuthorized = true

              if(role != null) {
                role match {
                  case RoleKind.Administrator => isAuthorized = user.role == RoleKind.Administrator
                  case RoleKind.Director => isAuthorized = user.role == RoleKind.Administrator ||
                    user.role == RoleKind.Director
                  case RoleKind.GeneralManager => isAuthorized = user.role == RoleKind.Administrator ||
                    user.role == RoleKind.Director || user.role == RoleKind.GeneralManager
                  case RoleKind.AccountManager => isAuthorized = user.role == RoleKind.Administrator ||
                    user.role == RoleKind.Director || user.role == RoleKind.GeneralManager || user.role == RoleKind.AccountManager
                }
              }

              if(isAuthorized) {
                promise.success(user)
              }
              else {
                promise.failure(new UnauthorizedException())
              }
            }
            else {
              promise.failure(new UnauthorizedException())
            }
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

      userQueryDao.getUserByEmail(enterDto.email).onComplete(r => {
        try {
          if (r.get.isEmpty) {
            throw new AuthenticationException()
          }
          val user = r.get.get
          if (!BCrypt.checkpw(enterDto.password, user.password)) {
            throw new AuthenticationException()
          }
          val accessKey = BCrypt.hashpw(UUID.randomUUID.toString, BCrypt.gensalt)
          userQueryDao.updateUserAccessKey(user.id, accessKey).onComplete(b => {
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
