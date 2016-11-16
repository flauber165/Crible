package persistence.dao.maps

import org.mongodb.scala.Document
//import service.domain._
import org.mongodb.scala.bson._


//extends Map[DashboardPortrait]
object DashboardPortraitMap  {
  /*def to(item: DashboardPortrait): Document = {
    Document("_id" -> item.id,
      "portraitDate" -> item.portraitDatetime,
      "portfolio" -> item.portfolio.,
      "managersRanking" -> item.managersRanking.asInstanceOf[BsonArray],
      "averageTerm" -> item.averageTerm,
      "averageRate" -> item.averageRate,
      "income" -> item.income,
      "overdue" -> item.overdue,
      "puctuality" -> item.puctuality,
      "portfolioRisk" -> item.portfolioRisk,
      "cedinConcentration" -> item.cedinConcentration,
      "payerConcentration" -> item.payerConcentration,
      "portifolioConcentration" -> item.portfolioConcentration,
      "user" -> item.userId)
  }*/

  /*def from(document: Document): DashboardPortrait = {
    DashboardPortrait(document.get("_id").get.asObjectId.getValue.toHexString,
      document.get("portraitDate").get.asDateTime,
      document.get("portfolio").get.asInstanceOf[Portfolio.Portfolio],
      document.get("managerRanking").get.asInstanceOf[ManagersRanking.ManagersRanking],
      document.get("averageRate").get.asInstanceOf[AverageRate.AverageRate],
      document.get("averageTerm").get.asString.getValue,
      document.get("income").get.asInstanceOf[Income.Income],
      document.get("overdue").get.asInstanceOf[Overdue.Overdue],
      document.get("puctuality").get.asInstanceOf[Punctuality.Punctuality],
      document.get("portfolioRisk").get.asString.getValue,
      document.get("cedinConcentration").get.asString.getValue,
      document.get("payerConcentration").get.asString.getValue,
      document.get("averageportifolioConcentrationTerm").get.asString.getValue,
      document.get("user").get.asObjectId.getValue.toHexString
      )
}*/
}