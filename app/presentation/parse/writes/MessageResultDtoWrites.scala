package presentation.parse.writes

import play.api.libs.json.Json
import presentation.MessageResultDto

object MessageResultDtoWrites {
  implicit val messageResultDtoWrites = Json.writes[MessageResultDto]
}
