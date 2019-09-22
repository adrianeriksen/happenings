package io.sixtysix.happenings.models

data class AuthenticatedUser(val id: Int, val email: String, val name: String, val token: String)