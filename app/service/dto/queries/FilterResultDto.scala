package service.dto.queries

import service.domain.User

case class FilterResultDto(
  index: String,
  data: Seq[User]
)
