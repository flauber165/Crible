package presentation.controllers

import domain.Bank
import play.api.mvc._
import scaldi.{Injectable, Injector}
import play.api.libs.json._
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import service.queries.BankQuery

class Application(implicit inj: Injector) extends Controller with Injectable {
  val bankQuery = inject [BankQuery]

  implicit val bankWrites = Json.writes[Bank]

  def index = Action.async {
    bankQuery.getAll().map(r => Ok(Json.toJson(r)))
  }
}
