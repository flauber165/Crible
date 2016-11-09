package presentation.parse.reads

import play.api.libs.json.Json
import service.dto.ChangePasswordDto

object ChangePasswordDtoReads {
  implicit val changePasswordDtoReads = Json.reads[ChangePasswordDto]
}
