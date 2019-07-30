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
    fun `Should not proceed when unauthenticated`() = testApplication {
        handleRequest(HttpMethod.Delete, "/api/events/2") {
            addHeader(HttpHeaders.ContentType, ContentType.Application.Json.toString())
        }.apply {
            assertEquals(HttpStatusCode.Unauthorized, response.status())
        }
    }
}