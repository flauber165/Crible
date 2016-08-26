package presentation.controllers

import com.google.inject.Inject
import play.api.libs.json.Json
import play.api.mvc.Controller
import presentation.Authorize
import service.domain.{Bank, RoleKind, User}
import service.dto.EnterDto
import service.dto.queries.{FilterResultDto, UserFilterDto}
import service.queries.{BankQueryService, UserQueryService}
import play.api.libs.concurrent.Execution.Implicits.defaultContext

class UserQueryController @Inject()(authorize: Authorize, service: UserQueryService) extends Controller {

  implicit val userFilterDtoReads = Json.reads[UserFilterDto]
  implicit val userWrites = Json.writes[User]
  implicit val filterResultDtoWrites = Json.writes[FilterResultDto]

  def filter = authorize.role(RoleKind.Administrator).async { request =>
    service.filter(userFilterDtoReads.reads(request.body.asJson.get).get).map(r => Ok(Json.toJson(r)))
  }
}