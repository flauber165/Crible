package presentation.parse.writes

import play.api.libs.functional.syntax._
import play.api.libs.json.{JsPath, Writes}
import service.dto.queries.FilterResultDto

object FilterResultDtoWrites {
  implicit def filterResultDtoWrites[T : Writes]: Writes[FilterResultDto[T]] = (
    (JsPath \ "index").write[String] and
      (JsPath \ "data").write[Seq[T]]
    )(unlift(FilterResultDto.unapply[T]))
}
