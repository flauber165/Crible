package presentation

import domain.Bank
import play.api.libs.json.Json

trait JsonParse {
  implicit val bankWrites = Json.writes[Bank]
}
