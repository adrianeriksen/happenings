package io.sixtysix.happenings.exceptions

import io.ktor.http.HttpStatusCode

open class AuthenticationException(val status: HttpStatusCode, override val message: String) : Exception()

val InvalidCredentialsError = AuthenticationException(
    HttpStatusCode.Unauthorized,
    "The username and password combination is invalid.")