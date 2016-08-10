package presentation.controllers

import com.google.inject.Inject
import service.domain.Bank
import play.api.mvc._
import play.api.libs.json._
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import presentation.Authorize
import service.queries.BankQueryService

class Application @Inject()(authorize: Authorize, service: BankQueryService) extends Controller {

  implicit val bankWrites = Json.writes[Bank]

  def index = authorize.async {
    service.getAll().map(r => Ok(Json.toJson(r)))
  }
}
