package presentation.controllers

import com.google.inject.Inject
import play.api.libs.json.{JsPath, Json, Writes}
import play.api.mvc.Controller
import presentation.Authorize
import service.domain.{Bank, RoleKind, User}
import service.dto.EnterDto
import service.dto.queries.{FilterResultDto, UserFilterDto}
import service.queries.{BankQueryService, UserQueryService}
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.json._
import play.api.libs.functional.syntax._

class UserQueryController @Inject()(authorize: Authorize, service: UserQueryService) extends Controller {

  implicit val userFilterDtoReads = Json.reads[UserFilterDto]
  implicit val userWrites = Json.writes[User]

  implicit def filterResultDtoWrites[T : Writes]: Writes[FilterResultDto[T]] = (
    (JsPath \ "index").write[String] and
      (JsPath \ "data").write[Seq[T]]
    )(unlift(FilterResultDto.unapply[T]))

  def filter = authorize.role(RoleKind.Administrator).async { request =>
    service.filter(userFilterDtoReads.reads(request.body.asJson.get).get).map(r => Ok(Json.toJson(r)))
  }
}