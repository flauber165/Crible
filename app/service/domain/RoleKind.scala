package service.domain

object RoleKind extends Enumeration {
  type RoleKind = Value
  val Administrator, Director,GeneralManager,AccountManager = Value
}