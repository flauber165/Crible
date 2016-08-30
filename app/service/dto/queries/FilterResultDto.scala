package service.dto.queries

case class FilterResultDto[T](
  index: String,
  data: Seq[T]
)
