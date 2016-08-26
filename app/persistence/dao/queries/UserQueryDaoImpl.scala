package persistence.dao.queries

import com.datastax.driver.core.{PagingState, Row}
import com.websudos.phantom.dsl.{ConsistencyLevel, _}
import persistence.maps.UserMap
import service.dao.queries.QueryDao
import service.domain.User
import service.dto.queries.{FilterResultDto, UserFilterDto}
import scala.collection.JavaConversions._
import scala.collection.mutable.ListBuffer
import scala.concurrent.{Future, Promise}
import scala.util.control.Breaks

private[persistence] abstract class UserQueryDaoImpl extends UserMap with QueryDao[User, UserFilterDto] with RootConnector {
  def filter(dto: UserFilterDto): Future[FilterResultDto] = {
    select.consistencyLevel_=(ConsistencyLevel.ONE).future(e => {
      var statement = e.setFetchSize(dto.count)

      if (dto.index.nonEmpty) {
        statement = statement.setPagingState(PagingState.fromString(dto.index.get))
      }

      statement
    }).map(rs => {
      val list = ListBuffer[User]()

      var remaining = rs.getAvailableWithoutFetching

      val loop = new Breaks

      loop.breakable {
        for(row <- rs){
          list += fromRow(row)
          remaining -= 1
          if(remaining == 0){
            loop.break
          }
        }
      }

      var index: String = null

      if(rs.getExecutionInfo.getPagingState != null) {
        index = rs.getExecutionInfo.getPagingState.toString
      }

      FilterResultDto(index, list)
    })
  }
}