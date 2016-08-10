package service

import java.math.BigInteger
import java.security.MessageDigest
import java.util.Base64

import com.google.inject.Inject
import play.api.i18n.{I18nSupport, Messages, MessagesApi}
import service.dao.queries.UserQueryDao
import service.dto.{EnterDto, EnterResultDto}
import play.api.libs.concurrent.Execution.Implicits.defaultContext

import scala.concurrent.Future

class AuthenticationService @Inject()(val messagesApi: MessagesApi, userQueryDao: UserQueryDao) extends I18nSupport {

  val messageDigest = MessageDigest.getInstance("MD5");
  val charsetName = "utf-8"
  val encoder = Base64.getEncoder

  def enter(enterDto: EnterDto): Future[EnterResultDto] = {
    val password = new BigInteger(1, messageDigest.digest(enterDto.password.getBytes)).toString(16)
    userQueryDao.getUserByEmailAndPassword(enterDto.email, password).map(r => {
      if(r.isEmpty) {
        throw new Exception(Messages("authenticationFailed"))
      }
      val user = r.get
      EnterResultDto(encoder.encodeToString(s"${user.id}:${user.password}".getBytes(charsetName)), r.get.name)
    })
  }
}
