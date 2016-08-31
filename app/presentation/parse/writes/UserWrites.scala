package presentation.parse.writes

import play.api.libs.json.Json
import service.domain.User

object UserWrites {
  implicit val userWrites = Json.writes[User]
}
