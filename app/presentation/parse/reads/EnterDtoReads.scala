package presentation.parse.reads

import play.api.libs.json.Json
import service.dto.EnterDto

object EnterDtoReads {
  implicit val enterDtoReads = Json.reads[EnterDto]
}
