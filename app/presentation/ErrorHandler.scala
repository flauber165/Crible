package presentation

import javax.inject.Singleton

import com.google.inject.Inject
import com.wix.accord.Descriptions.Explicit
import play.api.http.HttpErrorHandler
import play.api.i18n.{I18nSupport, Messages, MessagesApi}
import play.api.libs.json.Json
import play.api.mvc.Results._
import play.api.mvc._
import service.exceptions.{I18nException, UnauthorizedException, ValidatorException}
import scala.concurrent._
import presentation.parse.writes.MessageResultDtoWrites._
import presentation.parse.writes.ErrorResultDtoWrites._

@Singleton
class ErrorHandler @Inject()(val messagesApi: MessagesApi) extends HttpErrorHandler with I18nSupport {
  def onClientError(request: RequestHeader, statusCode: Int, message: String) = {
    var resultMessage: String = null
    statusCode match {
      case 404 => resultMessage = "resourceNotFound"
      case _ => resultMessage = message
    }
    Future.successful(
      Status(statusCode)(Json.toJson(MessageResultDto(Messages(resultMessage))))
    )
  }

  def onServerError(request: RequestHeader, exception: Throwable) = {
    var result: Result = null

    exception match {
      case validatorException: ValidatorException => result =
        InternalServerError(Json.toJson(ErrorResultDto(Messages("validatorException"),
          validatorException.failure.violations.map(e => { e.description match {
              case explicit: Explicit => Messages(explicit.description)
              case _ => e.description.toString
            }}).toSeq)))
      case i18nException: I18nException => result =
        InternalServerError(Json.toJson(MessageResultDto(Messages(exception.getMessage))))
      case unauthorizedException: UnauthorizedException => result =
        Unauthorized(Json.toJson(MessageResultDto(Messages("unauthorized"))))
      case _ => result = InternalServerError(Json.toJson(MessageResultDto(exception.getMessage)))
    }

    Future.successful(result)
  }
}
