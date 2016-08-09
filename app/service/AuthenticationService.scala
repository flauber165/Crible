package service

import java.util.Base64
import com.google.inject.Inject
import service.dao.queries.UserQueryDao
import service.dto.{EnterDto, EnterResultDto}
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import scala.concurrent.Future

class AuthenticationService @Inject()(userQueryDao: UserQueryDao) {

  val charsetName = "utf-8"
  val encoder = Base64.getEncoder

  def enter(enterDto: EnterDto): Future[EnterResultDto] = {
    userQueryDao.getUserByEmailAndPassword(enterDto.email, enterDto.password).map(r => {
      val user = r.get
      EnterResultDto(encoder.encodeToString(s"${user.id}:${user.password}".getBytes(charsetName)), r.get.name)
    })
  }
}
