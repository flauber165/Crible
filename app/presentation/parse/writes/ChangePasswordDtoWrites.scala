package presentation.parse.writes

import play.api.libs.json.Json
import service.dto.ChangePasswordDto

object ChangePasswordDtoWrites {
  implicit val changePasswordDtoWrites = Json.writes[ChangePasswordDto]
}
