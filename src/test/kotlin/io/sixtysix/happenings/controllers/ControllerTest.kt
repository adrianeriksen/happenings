package io.sixtysix.happenings.controllers

import io.ktor.config.MapApplicationConfig
import io.ktor.server.testing.TestApplicationEngine
import io.ktor.server.testing.withTestApplication
import io.sixtysix.happenings.testableModule

abstract class ControllerTest {

    fun testApplication(callback: TestApplicationEngine.() -> Unit): Unit =
        withTestApplication({
            (environment.config as MapApplicationConfig).apply {
                put("jwt.domain", "http://localhost:8080")
                put("jwt.secret", "95hZ4uUTZ7MUVdj2eUCAHXsLxsq7fERz")
            }
            testableModule()
        }, callback)
}