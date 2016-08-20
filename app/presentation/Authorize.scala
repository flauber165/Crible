package presentation

import com.google.inject.Inject
import play.api.mvc._
import service.AuthenticationService
import service.domain.RoleKind.RoleKind
import service.exceptions._
import scala.concurrent.{Future, Promise}
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import scala.util.{Failure, Success}

class Authorize @Inject()(service: AuthenticationService) extends ActionBuilder[Request] {

  var role: RoleKind = _

  def role(role: RoleKind): Authorize = {
    this.role = role
    this
  }

  def invokeBlock[A](request: Request[A], block: (Request[A]) => Future[Result]) = {
    val promise = Promise[Result]
    val authorization = request.headers.get("Authorization")
    if(authorization.nonEmpty) {
      service.check(authorization.get, role).onComplete({
        case Success(r) => promise.completeWith(block(request))
        case Failure(t) => promise.failure(t)
      })
    }
    else {
      promise.failure(new UnauthorizedException())
    }
    promise.future
  }
}