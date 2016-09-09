package service.domain

import java.util.UUID
import service.domain.RoleKind.RoleKind

case class User(
  id: String,
  name: String,
  email: String,
  password: String,
  accessKey: Option[String],
  role: RoleKind
)
