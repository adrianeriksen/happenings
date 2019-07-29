package io.sixtysix.happenings.controllers

import io.ktor.config.MapApplicationConfig
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.server.testing.TestApplicationEngine
import io.ktor.server.testing.handleRequest
import io.ktor.server.testing.withTestApplication
import io.sixtysix.happenings.testableModule
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class EventsControllerTests {

    @Test
    fun `Should not proceed when unauthenticated`() = testApplication {
        handleRequest(HttpMethod.Delete, "/api/events/2") {
            addHeader(HttpHeaders.ContentType, ContentType.Application.Json.toString())
        }.apply {
            assertEquals(HttpStatusCode.Unauthorized, response.status())
        }
    }

    private fun testApplication(callback: TestApplicationEngine.() -> Unit): Unit =
        withTestApplication({
            (environment.config as MapApplicationConfig).apply {
                put("jwt.domain", "http://localhost:8080")
                put("jwt.secret", "95hZ4uUTZ7MUVdj2eUCAHXsLxsq7fERz")
            }
            testableModule()
        }, callback)
}