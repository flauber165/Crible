package service

import org.mongodb.scala._
import org.mongodb.scala.bson.conversions._
import org.mongodb.scala.model.Filters
import scala.collection.mutable.ListBuffer

object FilterExtensions {
  implicit def listBufferExtensions(listBuffer: ListBuffer[Bson]) = new {
    def and(): Bson = Filters.and(listBuffer:_*)
  }

  implicit def collectionExtensions(collection: MongoCollection[Document]) = new {
    def andListBuffer(listBuffer: ListBuffer[Bson]): FindObservable[Document] = {
       if(listBuffer.nonEmpty) collection.find(listBuffer.and) else collection.find()
    }
  }
}