package presentation.controllers

import service.domain.Bank
import play.api.mvc._
import scaldi.{Injectable, Injector}
import play.api.libs.json._
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import service.queries.BankQueryService

class Application(implicit inj: Injector) extends Controller with Injectable {

  implicit val bankWrites = Json.writes[Bank]

  val service = inject [BankQueryService]

  def index = Action.async {
    service.getAll().map(r => Ok(Json.toJson(r)))
  }
}
