package persistence.dao.queries

import com.google.inject.Inject
import com.google.inject.name.Named
import org.mongodb.scala._
import org.mongodb.scala.bson.conversions.Bson
import org.mongodb.scala.model.Filters._
import persistence.dao.maps.UserMap
import service.dao.queries.QueryDao
import service.domain.User
import service.dto.queries.UserFilterDto
import play.api.libs.concurrent.Execution.Implicits.defaultContext

import scala.collection.mutable.ListBuffer
import scala.concurrent.Future

private[persistence] class UserQueryDaoImpl @Inject()(@Named("user") collection: MongoCollection[Document]) extends QueryDao[User, UserFilterDto] {
  def filter(dto: UserFilterDto): Future[Seq[User]] = {

    var filters = ListBuffer[Bson]()

    if (dto.name.nonEmpty) {
      filters += equal("name", dto.name.get)
    }

    if (dto.email.nonEmpty) {
      filters += equal("email", dto.email.get)
    }

    collection.find().skip(dto.index).limit(dto.count).toFuture()
      .map(d => {
        val list = ListBuffer[User]()

        for(row <- d.seq){
          list += UserMap.from(row)
        }

        list
      })
  }
}