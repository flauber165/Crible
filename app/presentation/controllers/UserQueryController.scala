package presentation.controllers

import com.google.inject.Inject
import play.api.libs.json.Json
import play.api.mvc.Controller
import presentation.Authorize
import service.domain.RoleKind
import service.queries.UserQueryService
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import presentation.parse.reads.UserFilterDtoReads._
import presentation.parse.writes.UserWrites._

class UserQueryController @Inject()(authorize: Authorize, service: UserQueryService) extends Controller {
  def filter = authorize.role(RoleKind.Administrator).async { request =>
    service.filter(userFilterDtoReads.reads(request.body.asJson.get).get).map(r => Ok(Json.toJson(r)))
  }
}