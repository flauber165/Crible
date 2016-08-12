package service

import java.util.{Base64, UUID}
import com.google.inject.Inject
import org.mindrot.jbcrypt.BCrypt
import service.dao.queries.UserQueryDao
import service.dto.{EnterDto, EnterResultDto}
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import service.domain.RoleKind.RoleKind
import service.domain.User
import com.wix.accord._
import dsl._
import scala.concurrent.{Future, Promise}
import ServiceValidator.validateAndThrow

class AuthenticationService @Inject()(userQueryDao: UserQueryDao) {

  val charsetName = "utf-8"
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
        userQueryDao.getUserByIdAndPasswordAndRole(UUID.fromString(array(0)), array(1), role).onComplete(r => {
          if(r.get.nonEmpty) {
            promise.success(r.get.get)
          }
          else {
            promise.success(null)
          }
        })
      }
      else {
        promise.success(null)
      }
    }
    catch {
      case e: Throwable => promise.success(null)
    }

    promise.future
  }

  def enter(enterDto: EnterDto): Future[EnterResultDto] = {
    validateAndThrow(enterDto)

    //Gerar senha Para teste... remover em produção...
    println(BCrypt.hashpw(enterDto.password, BCrypt.gensalt))

    userQueryDao.getUserByEmail(enterDto.email).map(r => {
      if(r.isEmpty) {
        throw I18nException("authenticationFailed")
      }
      val user = r.get
      if(!BCrypt.checkpw(enterDto.password, user.password)) {
        throw I18nException("authenticationFailed")
      }
      EnterResultDto(encoder.encodeToString(s"${user.id}:${user.password}".getBytes(charsetName)), r.get.name)
    })
  }
}
