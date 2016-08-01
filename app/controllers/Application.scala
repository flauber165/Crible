package controllers

import domain.Bank
import net.sf.ehcache.CacheManager
import persistence.ProductionDatabase
import play.api.mvc._
import scaldi.{Injectable, Injector}
import play.api.cache.CacheApi
import play.api.libs.json._
import play.api.libs.concurrent.Execution.Implicits.defaultContext

class Application(implicit inj: Injector) extends Controller with Injectable {
  val cache = inject [CacheApi]

  val cacheManager = inject [CacheManager]


  implicit val locationWrites = new Writes[Bank] {
    def writes(bank: Bank) = Json.obj(
      "id" -> bank.id,
      "name" -> bank.name
    )
  }

  def index = Action.async {
    ProductionDatabase.bankDao.getAll().map(r => Ok(Json.toJson(r)))
  }
}
