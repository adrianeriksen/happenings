package io.sixtysix.happenings.controllers

import io.ktor.config.MapApplicationConfig
import io.ktor.server.testing.TestApplicationEngine
import io.ktor.server.testing.withTestApplication
import io.sixtysix.happenings.models.UserCredentials
import io.sixtysix.happenings.services.AuthService
import io.sixtysix.happenings.testableModule

interface ControllerTest {

    fun testApplication(callback: TestApplicationEngine.() -> Unit): Unit =
        withTestApplication({
            (environment.config as MapApplicationConfig).apply {
                put("jwt.domain", "http://localhost:8080")
                put("jwt.secret", "95hZ4uUTZ7MUVdj2eUCAHXsLxsq7fERz")
            }
            testableModule()
        }, callback)

    fun generateAuthorizationToken(): String {
        val authService = AuthService("http://localhost:8080", "95hZ4uUTZ7MUVdj2eUCAHXsLxsq7fERz")
        val user = UserCredentials(1, "adrian@example.io", "")

        return authService.generateToken(user)
    }
}