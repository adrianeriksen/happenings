package io.sixtysix.happenings.controllers

import io.ktor.config.MapApplicationConfig
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.server.testing.TestApplicationEngine
import io.ktor.server.testing.handleRequest
import io.ktor.server.testing.withTestApplication
import io.sixtysix.happenings.module
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class UserControllerTests {

    @Test
    fun `Return 201 created`() = testApplication {
        with(handleRequest(HttpMethod.Post, "/api/user")) {
            assertEquals(HttpStatusCode.Created, response.status())
        }
    }

    private fun testApplication(callback: TestApplicationEngine.() -> Unit): Unit =
        withTestApplication({
            (environment.config as MapApplicationConfig).apply {
                put("jwt.domain", "http://localhost:8080")
                put("jwt.secret", "95hZ4uUTZ7MUVdj2eUCAHXsLxsq7fERz")
            }
            module()
        }, callback)
}