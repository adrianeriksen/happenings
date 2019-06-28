package io.sixtysix.happenings

import io.ktor.application.*
import io.ktor.features.ContentNegotiation
import io.ktor.gson.gson
import io.ktor.routing.routing
import io.ktor.util.KtorExperimentalAPI
import io.sixtysix.happenings.controllers.authController
import io.sixtysix.happenings.controllers.eventsController
import io.sixtysix.happenings.utils.DateTimeAdapter
import io.sixtysix.happenings.services.DatabaseFactory
import io.sixtysix.happenings.services.EventService
import io.sixtysix.happenings.services.UserService
import org.joda.time.DateTime

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused")
fun Application.module() {
    install(ContentNegotiation) {
        gson {
            registerTypeAdapter(DateTime::class.java, DateTimeAdapter())
        }
    }

    DatabaseFactory.init()

    val eventService = EventService()
    val userService = UserService()

    routing {
        authController(userService)
        eventsController(eventService)
    }
}
