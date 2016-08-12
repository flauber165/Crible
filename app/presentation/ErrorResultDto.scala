package presentation

case class ErrorResultDto(
  message: String,
  errors: Seq[String]
)