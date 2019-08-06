package io.sixtysix.happenings.controllers

import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.server.testing.handleRequest
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class EventsControllerTest : ControllerTest {

    @Test
    fun `Return 401 Unauthorized when unauthenticated`() = testApplication {
        handleRequest(HttpMethod.Delete, "/api/events/1") {
            addHeader(HttpHeaders.ContentType, ContentType.Application.Json.toString())
        }.apply {
            assertEquals(HttpStatusCode.Unauthorized, response.status())
        }
    }

    @Test
    fun `Return 404 Not Found when provided invalid id`() = testApplication {
        val authToken = generateAuthorizationToken()

        handleRequest(HttpMethod.Delete, "/api/events/42") {
            addHeader(HttpHeaders.Authorization, "Bearer $authToken")
            addHeader(HttpHeaders.ContentType, ContentType.Application.Json.toString())
        }.apply {
            assertEquals(HttpStatusCode.NotFound, response.status())
        }
    }

    @Test
    fun `Return 403 Forbidden when authenticated user is not owner`() = testApplication {
        val authToken = generateAuthorizationToken()

        handleRequest(HttpMethod.Delete, "/api/events/2") {
            addHeader(HttpHeaders.Authorization, "Bearer $authToken")
            addHeader(HttpHeaders.ContentType, ContentType.Application.Json.toString())
        }.apply {
            assertEquals(HttpStatusCode.Forbidden, response.status())
        }
    }

    @Test
    fun `Return 204 No Content when delete was successful`() = testApplication {
        val authToken = generateAuthorizationToken()

        handleRequest(HttpMethod.Delete, "/api/events/1") {
            addHeader(HttpHeaders.Authorization, "Bearer $authToken")
            addHeader(HttpHeaders.ContentType, ContentType.Application.Json.toString())
        }.apply {
            assertEquals(HttpStatusCode.NoContent, response.status())
        }
    }
}