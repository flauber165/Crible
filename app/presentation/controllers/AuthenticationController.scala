package presentation.controllers

import com.google.inject.Inject
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import service.AuthenticationService
import service.dto.{EnterDto, EnterResultDto}
import play.api.libs.concurrent.Execution.Implicits.defaultContext

class AuthenticationController @Inject()(service: AuthenticationService) extends Controller {

  implicit val enterReads = Json.reads[EnterDto]
  implicit val enterResultWrites = Json.writes[EnterResultDto]

  def enter = Action.async { request =>
    service.enter(enterReads.reads(request.body.asJson.get).get).map(r => Ok(Json.toJson(r)))
  }
}