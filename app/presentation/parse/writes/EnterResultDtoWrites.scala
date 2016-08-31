package presentation.parse.writes

import play.api.libs.json.Json
import service.dto.EnterResultDto

object EnterResultDtoWrites {
  implicit val enterResultDtoWrites = Json.writes[EnterResultDto]
}
