package service.queries

import com.google.inject.Inject
import com.google.inject.name.Named
import org.mongodb.scala._
import org.mongodb.scala.bson.conversions._
import org.mongodb.scala.model.Filters._
import persistence.dao.maps.UserMap
import service.domain.User
import service.dto.queries.UserFilterDto
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import scala.collection.mutable.ListBuffer
import scala.concurrent.Future

class UserQueryService @Inject()(@Named("user") collection: MongoCollection[Document]) {
  def filter(dto: UserFilterDto): Future[Seq[User]] = {
    var filters = ListBuffer[Bson]()

    if (dto.name.get != "") {
      filters += regex("name", s".*${dto.name.get}.*")
    }

    if (dto.email.get != "") {
      filters += regex("email", s".*${dto.email.get}.*")
    }

    var query: FindObservable[Document] = null

    if (filters.nonEmpty) {
      query = collection.find(and(filters:_*))
    }
    else {
      query = collection.find()
    }

    query.skip(dto.index).limit(dto.count).toFuture().map(d => {
      val list = ListBuffer[User]()

      for(row <- d.seq){
        list += UserMap.from(row)
      }

      list
    })
  }
}