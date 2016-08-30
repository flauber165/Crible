package persistence.dao

import com.datastax.driver.core.{PagingState, Session}
import com.websudos.phantom.builder.query.ExecutableStatement
import com.websudos.phantom.connectors.KeySpace
import com.websudos.phantom.dsl.Row

import scala.collection.JavaConversions._
import service.dto.queries.{FilterDto, FilterResultDto}

import scala.collection.mutable.ListBuffer
import scala.concurrent.{ExecutionContextExecutor, Future}
import scala.util.control.Breaks

private[persistence] object QueryExtensions {
  implicit def executableStatementExtensions(executableStatement: ExecutableStatement) = new {
    def page[T](dto: FilterDto)(implicit session: Session, keySpace: KeySpace, executor: ExecutionContextExecutor, fromRow: Row => T): Future[FilterResultDto[T]] = {

      executableStatement.future(e => {
        var statement = e.setFetchSize(dto.count)

        if (dto.index.nonEmpty) {
          statement = statement.setPagingState(PagingState.fromString(dto.index.get))
        }

        statement
      }).map(rs => {
        val list = ListBuffer[T]()

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
}