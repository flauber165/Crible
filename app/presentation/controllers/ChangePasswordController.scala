package presentation.controllers

import com.google.inject.Inject
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import service.ChangePasswordService
import service.ResetPasswordService
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import presentation.parse.reads.ChangePasswordDtoReads._

/**
  * Created by italosantana on 08/11/16.
  */
class ChangePasswordController @Inject()(changeService: ChangePasswordService, resetService: ResetPasswordService) extends Controller {
  def changePassword = Action.async { request =>
    changeService.changePassword(changePasswordDtoReads.reads(request.body.asJson.get).get).map(r => Ok(Json.toJson(r)))
  }

  def resetPassword(id: Option[String], token: Option[String]) = Action.async { request =>
    resetService.resetPassword(id, token).map(r => Ok(Json.toJson(r)))
 }
}
