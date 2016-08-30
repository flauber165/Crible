package service.dao.queries

import service.dto.queries.{FilterDto, FilterResultDto}

import scala.concurrent.Future

trait QueryDao[T, TDto <: FilterDto] {
  def filter(dto: TDto): Future[FilterResultDto[T]]
}
