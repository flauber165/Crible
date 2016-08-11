package presentation

import com.google.inject.Inject
import play.api.i18n.{I18nSupport, Messages, MessagesApi}
import play.api.mvc.Results._
import play.api.mvc._
import service.AuthenticationService
import service.domain.RoleKind.RoleKind

import scala.concurrent.{Future, Promise}
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.json.Json

class Authorize @Inject()(val messagesApi: MessagesApi, service: AuthenticationService) extends ActionBuilder[Request]
  with I18nSupport {

  implicit val errorResultDtoWrites = Json.writes[ErrorResultDto]

  var role: RoleKind = _

  def role(role: RoleKind): Authorize = {
    this.role = role
    this
  }

  def invokeBlock[A](request: Request[A], block: (Request[A]) => Future[Result]) = {
    val promise = Promise[Result]
    val authorization = request.headers.get("Authorization")
    if(authorization.nonEmpty) {
      service.check(authorization.get, role).onComplete(r => {
        if (r.get != null) {
          promise.completeWith(block(request))
        }
        else {
          promise.success(Unauthorized(Json.toJson(ErrorResultDto(Messages("unauthorized")))))
        }
      })
    }
    else {
      promise.success(Unauthorized(Json.toJson(ErrorResultDto(Messages("unauthorized")))))
    }
    promise.future
  }
}