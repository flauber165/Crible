package service


import java.util.{Base64, UUID}
import com.google.inject.Inject
import org.mindrot.jbcrypt.BCrypt
import play.api.i18n.{I18nSupport, Messages, MessagesApi}
import service.dao.queries.UserQueryDao
import service.dto.{EnterDto, EnterResultDto}
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import service.domain.RoleKind.RoleKind
import service.domain.User

import scala.concurrent.{Future, Promise}

class AuthenticationService @Inject()(val messagesApi: MessagesApi, userQueryDao: UserQueryDao) extends I18nSupport {

  val charsetName = "utf-8"
  val encoder = Base64.getEncoder
  val decoder = Base64.getDecoder

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
    //Gerar senha Para teste... remover em produção...
    println(BCrypt.hashpw(enterDto.password, BCrypt.gensalt))

    userQueryDao.getUserByEmail(enterDto.email).map(r => {
      if(r.isEmpty) {
        throw new Exception(Messages("authenticationFailed"))
      }
      val user = r.get
      if(!BCrypt.checkpw(enterDto.password, user.password)) {
        throw new Exception(Messages("authenticationFailed"))
      }
      EnterResultDto(encoder.encodeToString(s"${user.id}:${user.password}".getBytes(charsetName)), r.get.name)
    })
  }
}
