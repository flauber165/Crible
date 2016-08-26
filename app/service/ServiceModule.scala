package service

import service.queries.{BankQueryService, UserQueryService}
import com.google.inject.AbstractModule

class ServiceModule extends AbstractModule {
  def configure() = {
    bind(classOf[AuthenticationService])
    bind(classOf[UserQueryService])
    bind(classOf[BankQueryService])
  }
}