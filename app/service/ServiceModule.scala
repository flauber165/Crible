package service

import scaldi.Module
import service.queries.BankQueryService

class ServiceModule extends Module  {
  bind[AuthenticationService] to inject[AuthenticationService]
  bind[BankQueryService] to injected[BankQueryService]
}