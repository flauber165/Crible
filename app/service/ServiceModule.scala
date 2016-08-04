package service

import scaldi.Module
import service.queries.BankQuery

class ServiceModule extends Module  {
  bind[BankQuery] to injected[BankQuery]
}