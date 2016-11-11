package service


import java.util.{Base64, UUID}
import com.google.inject.Inject
import com.google.inject.name.Named
import com.wix.accord.dsl._
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.mailer._
import org.mongodb.scala._
import org.mongodb.scala.bson.BsonObjectId
import org.mongodb.scala.model.Filters._
import org.mongodb.scala.model.Updates._
import persistence.dao.maps.UserMap
import service.dto.ChangePasswordDto
import scala.concurrent.{Future, Promise}

/**
  * Created by italosantana on 08/11/16.
  */
class ChangePasswordService @Inject()(mailer : MailerClient,@Named("user") collection: MongoCollection[Document]) {

  val encoder = Base64.getEncoder
  val decoder = Base64.getDecoder



  implicit val changePasswordDtoValidator = validator[ChangePasswordDto] { e =>
    e.email as "emailEmptyField" is notEmpty
  }

  def changePassword(changePass: ChangePasswordDto): Future[String] = {
    val promise = Promise[String]

    try{

      collection.find(equal("email", changePass.email)).first.toFuture.onComplete(r => {
        try{

          if (r.get.isEmpty) {
            throw new Throwable
          }

          val user = UserMap.from(r.get.head)
          val accessKey = UUID.randomUUID.toString
          val link = "http://localhost:9000/resetPassword?id=" + user.id + "&token=" + accessKey

          sendEmail(mailer,link, user.email)

          collection.updateOne(equal("_id", BsonObjectId(user.id)), set("accessKey", accessKey)).head.onComplete(b => {
            promise.success("Link para recuperação de senha anviado para: " + user.email)
          })

      }
      catch {
          case e: Throwable => promise.failure(e)
        }
      })
    }
    catch {
      case e: Throwable => promise.failure(e)
    }
    promise.future
  }

  def sendEmail (mailer: MailerClient,link: String, mailDestination: String){

    val email = Email(
        "Administração Criblé - Recuperação de Senha",
        "italo@crible.com.br",
        Seq(mailDestination),
        bodyText = Some("Recuperação de Senha"),
        bodyHtml = Some("<html><body><p>Para resetar sua senha clique no link: <a href="+link+"> Recuperar Senha</a></p></body></html>")
    )

    mailer.send(email)
  }
}
