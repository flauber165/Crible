package presentation.controllers

import com.google.inject.Inject
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import service.ChangePasswordService
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import presentation.parse.reads.ChangePasswordDtoReads._

/**
  * Created by italosantana on 08/11/16.
  */
class ChangePasswordController @Inject()(service: ChangePasswordService) extends Controller {
  def changePassword = Action.async { request =>
    service.changePassword(changePasswordDtoReads.reads(request.body.asJson.get).get).map(r => Ok(Json.toJson(r)))
  }
}
