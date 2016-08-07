package presentation.controllers

import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import scaldi.{Injectable, Injector}
import service.AuthenticationService
import service.dto.{EnterDto, EnterResultDto}
import play.api.libs.concurrent.Execution.Implicits.defaultContext

class AuthenticationController(implicit inj: Injector) extends Controller with Injectable {

  implicit val enterReads = Json.reads[EnterDto]
  implicit val enterResultWrites = Json.writes[EnterResultDto]

  val service = inject [AuthenticationService]

  def enter = Action.async { request =>
    service.enter(enterReads.reads(request.body.asJson.get).get).map(r => Ok(Json.toJson(r)))
  }
}