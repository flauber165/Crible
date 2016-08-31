package presentation.controllers

import com.google.inject.Inject
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import service.AuthenticationService
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import presentation.parse.reads.EnterDtoReads._
import presentation.parse.writes.EnterResultDtoWrites._

class AuthenticationController @Inject()(service: AuthenticationService) extends Controller {
  def enter = Action.async { request =>
    service.enter(enterDtoReads.reads(request.body.asJson.get).get).map(r => Ok(Json.toJson(r)))
  }
}