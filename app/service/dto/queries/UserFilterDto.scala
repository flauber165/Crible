package service.dto.queries

case class UserFilterDto(
  val name: Option[String],
  val email: Option[String],
  override val index: Option[String],
  override val count: Int
) extends FilterDto
