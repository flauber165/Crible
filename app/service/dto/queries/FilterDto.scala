package service.dto.queries

trait FilterDto {
  val index: Option[String]
  val count: Int
}