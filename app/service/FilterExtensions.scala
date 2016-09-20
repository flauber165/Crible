package service

import com.mongodb.client.model.Filters
import org.mongodb.scala._
import org.mongodb.scala.bson.conversions._
import scala.collection.mutable.ListBuffer

object FilterExtensions {
  implicit def listBufferExtensions[T](listBuffer: ListBuffer[T]) = new {
    def in(fieldName: String): Bson = Filters.in(fieldName, listBuffer:_*)
  }

  implicit def bsonListBufferExtensions(listBuffer: ListBuffer[Bson]) = new {
    def and(): Bson = Filters.and(listBuffer:_*)
  }

  implicit def collectionExtensions(collection: MongoCollection[Document]) = new {
    def andListBuffer(listBuffer: ListBuffer[Bson]): FindObservable[Document] = {
       if(listBuffer.nonEmpty) collection.find(listBuffer.and) else collection.find()
    }
  }
}