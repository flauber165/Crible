package presentation

import javax.inject.Singleton

import play.api.http.HttpErrorHandler
import play.api.libs.json.Json
import play.api.mvc.Results._
import play.api.mvc._
import service.ValidatorException

import scala.concurrent._

@Singleton
class ErrorHandler extends HttpErrorHandler {

  implicit val errorResultDtoWrites = Json.writes[ErrorResultDto]

  def onClientError(request: RequestHeader, statusCode: Int, message: String) = {
    Future.successful(
      Status(statusCode)(Json.toJson(ErrorResultDto(message)))
    )
  }

  def onServerError(request: RequestHeader, exception: Throwable) = {
    var errorResultDto: ErrorResultDto = null

    exception match {
      case validatorException: ValidatorException => errorResultDto = ErrorResultDto(validatorException.failure.toString)
      case _ => errorResultDto = ErrorResultDto(exception.getMessage)
    }

    Future.successful(
      InternalServerError(Json.toJson(errorResultDto))
    )
  }
}
