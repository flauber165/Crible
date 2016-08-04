package presentation.controllers

import play.api.mvc._
import scaldi.{Injectable, Injector}
import play.api.libs.json._
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import presentation.JsonParse
import service.queries.BankQuery

class Application(implicit inj: Injector) extends Controller with JsonParse with Injectable {
  val bankQuery = inject [BankQuery]

  def index = Action.async {
    bankQuery.getAll().map(r => Ok(Json.toJson(r)))
  }
}
