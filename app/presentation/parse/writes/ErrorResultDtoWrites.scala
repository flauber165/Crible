package presentation.parse.writes

import play.api.libs.json.Json
import presentation.ErrorResultDto

object ErrorResultDtoWrites {
  implicit val errorResultDtoWrites = Json.writes[ErrorResultDto]
}
