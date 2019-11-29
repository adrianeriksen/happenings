package io.sixtysix.happenings.controllers

import com.google.gson.Gson
import io.ktor.http.ContentType
import io.ktor.http.Cookie
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.server.testing.TestApplicationEngine
import io.ktor.server.testing.handleRequest
import io.ktor.server.testing.setBody
import io.sixtysix.happenings.forms.LoginForm

const val SESSION_COOKIE_NAME = "SESSION_ID"

fun TestApplicationEngine.fetchSessionCookie(form: LoginForm): Cookie {
    handleRequest(HttpMethod.Post, "/api/auth/login") {
        addHeader(HttpHeaders.ContentType, ContentType.Application.Json.toString())
        setBody(Gson().toJson(form))
    }.apply {
        return response.cookies[SESSION_COOKIE_NAME]!!
    }
}