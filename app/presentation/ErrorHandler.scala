package presentation

import javax.inject.Singleton

import play.api.http.HttpErrorHandler
import play.api.libs.json.Json
import play.api.mvc.Results._
import play.api.mvc._

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
    Future.successful(
      InternalServerError(Json.toJson(ErrorResultDto(exception.getMessage)))
    )
  }
}
