package presentation.controllers

import com.google.inject.Inject
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import service.{ChangePasswordService, ResetPasswordService, UpdatePasswordService}
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import presentation.parse.reads.ChangePasswordDtoReads._

/**
  * Created by italosantana on 08/11/16.
  */
class ChangePasswordController @Inject()(changeService: ChangePasswordService, resetService: ResetPasswordService, updateService: UpdatePasswordService) extends Controller {

  // Envia email com URL de recuperação
  def changePassword = Action.async { request =>
    changeService.changePassword(changePasswordDtoReads.reads(request.body.asJson.get).get).map(r => Ok(Json.toJson(r)))
  }

  // Valida a URL de validação
  def resetPassword(id: Option[String], token: Option[String]) = Action.async { request =>
    resetService.resetPassword(id, token).map(r => Ok(Json.toJson(r)))
 }

  // Atualiza senha - Concluir
  def updatePassword = Action.async { request =>
    // mudar método
    updateService.updatePassword("5823563c0a7bf89f458b0e53", "123456").map(r => Ok(Json.toJson(r)))
  }


}
