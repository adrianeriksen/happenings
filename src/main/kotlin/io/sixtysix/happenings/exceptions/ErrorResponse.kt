package io.sixtysix.happenings.exceptions

import io.ktor.http.HttpStatusCode

data class ErrorResponse(val status: HttpStatusCode, val message: String)