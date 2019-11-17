package io.sixtysix.happenings

import io.ktor.application.*
import io.ktor.auth.Authentication
import io.ktor.auth.UnauthorizedResponse
import io.ktor.auth.session
import io.ktor.features.ContentNegotiation
import io.ktor.features.StatusPages
import io.ktor.gson.gson
import io.ktor.response.respond
import io.ktor.routing.routing
import io.ktor.sessions.Sessions
import io.ktor.sessions.cookie
import io.ktor.sessions.directorySessionStorage
import io.ktor.util.KtorExperimentalAPI
import io.sixtysix.happenings.controllers.authController
import io.sixtysix.happenings.controllers.eventsController
import io.sixtysix.happenings.controllers.userController
import io.sixtysix.happenings.exceptions.AuthenticationException
import io.sixtysix.happenings.exceptions.ErrorResponse
import io.sixtysix.happenings.services.*
import io.sixtysix.happenings.utils.DateTimeAdapter
import org.joda.time.DateTime
import java.io.File

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@KtorExperimentalAPI
fun Application.module(eventService: EventService, userService: UserService) {

    install(ContentNegotiation) {
        gson {
            registerTypeAdapter(DateTime::class.java, DateTimeAdapter())
        }
    }

    install(StatusPages) {
        exception<AuthenticationException> { cause ->
            call.respond(cause.status, ErrorResponse(cause.status, cause.message))
        }
    }

    install(Authentication) {
        session<UserSession> {
            validate { it }
            challenge { call.respond(UnauthorizedResponse()) }
        }
    }

    install(Sessions) {
        cookie<UserSession>(
            "SESSION_ID",
            directorySessionStorage(File(".sessions"), cached = true)
        ) {
            cookie.path = "/"
        }
    }

    routing {
        authController(userService)
        eventsController(eventService, userService)
        userController(userService)
    }
}

@KtorExperimentalAPI
fun Application.regularModule() {
    DatabaseFactory.init()

    val eventService = EventServiceImpl()
    val userService = UserServiceImpl()

    module(eventService, userService)
}

@KtorExperimentalAPI
fun Application.testableModule() {
    val eventService = EventServiceMock()
    val userService = UserServiceMock()

    module(eventService, userService)
}