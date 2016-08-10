package presentation

import com.google.inject.Inject
import play.api.mvc.Results._
import play.api.mvc._
import service.AuthenticationService
import service.domain.RoleKind.RoleKind
import scala.concurrent.{ Future, Promise }
import play.api.libs.concurrent.Execution.Implicits.defaultContext

class Authorize @Inject()(service: AuthenticationService) extends ActionBuilder[Request] {

  var role: RoleKind = _

  def role(role: RoleKind): Unit = {
      this.role = role
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
          promise.success(Unauthorized)
        }
      })
    }
    else {
      promise.success(Unauthorized)
    }
    promise.future
  }
}