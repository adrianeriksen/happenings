package io.sixtysix.happenings

import io.ktor.auth.Principal

data class UserSession(val id: Int) : Principal
