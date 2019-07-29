package io.sixtysix.happenings.controllers

import com.google.gson.Gson
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.server.testing.handleRequest
import io.ktor.server.testing.setBody
import io.sixtysix.happenings.forms.NewUserForm
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class UserControllerTest : ControllerTest() {

    private val gson = Gson();

    @Test
    fun `Return 201 created with correct input`() = testApplication {
        val payload = NewUserForm(
            email = "jonas@example.no",
            password = "chess underpants",
            name = "Jonas S.")

        handleRequest(HttpMethod.Post, "/api/user") {
            addHeader(HttpHeaders.ContentType, ContentType.Application.Json.toString())
            setBody(gson.toJson(payload))
        }.apply {
            assertEquals(HttpStatusCode.Created, response.status())
        }
    }

    @Test
    fun `Return 400 bad request with short password`() = testApplication {
        val payload = NewUserForm(
            email = "adrian@example.no",
            password = "moped ninja",
            name = "Adrian E.")

        handleRequest(HttpMethod.Post, "/api/user") {
            addHeader(HttpHeaders.ContentType, ContentType.Application.Json.toString())
            setBody(gson.toJson(payload))
        }.apply {
            assertEquals(HttpStatusCode.BadRequest, response.status())
        }
    }
}