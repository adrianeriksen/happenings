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
import io.sixtysix.happenings.accounts.AccountService
import io.sixtysix.happenings.accounts.AccountServiceImpl
import io.sixtysix.happenings.accounts.accountsResource
import io.sixtysix.happenings.controllers.authController
import io.sixtysix.happenings.controllers.eventsController
import io.sixtysix.happenings.controllers.userController
import io.sixtysix.happenings.exceptions.AuthenticationException
import io.sixtysix.happenings.exceptions.ErrorResponse
import io.sixtysix.happenings.services.*
import io.sixtysix.happenings.utils.DateTimeAdapter
import java.io.File
import java.time.LocalDateTime

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@KtorExperimentalAPI
fun Application.module(eventService: EventService, userService: UserService, accountService: AccountService) {

    install(ContentNegotiation) {
        gson {
            registerTypeAdapter(LocalDateTime::class.java, DateTimeAdapter())
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
        accountsResource(accountService)
        authController(userService)
        eventsController(eventService)
        userController(userService)
    }
}

@KtorExperimentalAPI
fun Application.regularModule() {
    DatabaseFactory.init()

    val eventService = EventServiceImpl()
    val userService = UserServiceImpl()
    val accountService = AccountServiceImpl()

    module(eventService, userService, accountService)
}

@KtorExperimentalAPI
fun Application.testableModule() {
    val eventService = EventServiceMock()
    val userService = UserServiceMock()
    val accountService = AccountServiceImpl()

    module(eventService, userService, accountService)
}