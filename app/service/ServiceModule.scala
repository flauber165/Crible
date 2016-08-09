package service

import service.queries.BankQueryService
import com.google.inject.AbstractModule

class ServiceModule extends AbstractModule {
  def configure() = {
    bind(classOf[AuthenticationService])
    bind(classOf[BankQueryService])
  }
}