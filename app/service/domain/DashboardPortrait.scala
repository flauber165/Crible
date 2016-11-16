package service.domain

import org.mongodb.scala.bson.BsonDateTime
import service.domain.Portfolio.Portfolio
import service.domain.Punctuality.Punctuality
import service.domain.AverageRate.AverageRate
import service.domain.ManagersRanking.ManagersRanking
import service.domain.Income.Income
import service.domain.Overdue.Overdue

/**
  * Created by italosantana on 12/11/16.
  */
case class DashboardPortrait
(
  id: String,
  portraitDatetime: BsonDateTime,
  portfolio: Portfolio,
  managersRanking: ManagersRanking,
  averageRate: AverageRate,
  averageTerm: String,
  income: Income,
  overdue: Overdue,
  puctuality: Punctuality,
  portfolioRisk: String,
  cedinConcentration: String,
  payerConcentration: String,
  portfolioConcentration: String,
  userId: String
)
