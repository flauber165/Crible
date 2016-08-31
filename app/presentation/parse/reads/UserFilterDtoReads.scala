package presentation.parse.reads

import play.api.libs.json.Json
import service.dto.queries.UserFilterDto

object UserFilterDtoReads {
  implicit val userFilterDtoReads = Json.reads[UserFilterDto]
}
