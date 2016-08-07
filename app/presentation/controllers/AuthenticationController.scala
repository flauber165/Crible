package presentation.controllers

import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import scaldi.{Injectable, Injector}
import service.AuthenticationService
import service.dto.EnterResult
import play.api.libs.concurrent.Execution.Implicits.defaultContext

class AuthenticationController(implicit inj: Injector) extends Controller with Injectable {

  implicit val enterResultWrites = Json.writes[EnterResult]

  val service = inject [AuthenticationService]

  def enter = Action.async {
    service.enter().map(r => Ok(Json.toJson(r)))
  }
}