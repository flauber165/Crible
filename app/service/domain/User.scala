package service.domain

import java.util.UUID
import service.domain.RoleKind.RoleKind

case class User(
  id: UUID,
  name: String,
  email: String,
  password: String,
  role: RoleKind
)
