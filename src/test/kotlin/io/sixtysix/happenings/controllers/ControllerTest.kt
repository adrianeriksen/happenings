package io.sixtysix.happenings.controllers

import io.ktor.server.testing.TestApplicationEngine
import io.ktor.server.testing.withTestApplication
import io.ktor.util.KtorExperimentalAPI
import io.sixtysix.happenings.testableModule

interface ControllerTest {

    @KtorExperimentalAPI
    fun testApplication(callback: TestApplicationEngine.() -> Unit): Unit =
        withTestApplication({
            testableModule()
        }, callback)
}