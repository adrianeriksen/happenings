package io.sixtysix.happenings

import io.ktor.application.*
import io.ktor.auth.Authentication
import io.ktor.auth.jwt.JWTPrincipal
import io.ktor.auth.jwt.jwt
import io.ktor.features.ContentNegotiation
import io.ktor.features.StatusPages
import io.ktor.gson.gson
import io.ktor.response.respond
import io.ktor.routing.routing
import io.ktor.util.KtorExperimentalAPI
import io.sixtysix.happenings.controllers.authController
import io.sixtysix.happenings.controllers.eventsController
import io.sixtysix.happenings.controllers.userController
import io.sixtysix.happenings.exceptions.AuthenticationException
import io.sixtysix.happenings.exceptions.ErrorResponse
import io.sixtysix.happenings.services.*
import io.sixtysix.happenings.utils.DateTimeAdapter
import org.joda.time.DateTime

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@KtorExperimentalAPI
fun Application.module(authService: AuthService, eventService: EventService, userService: UserService) {

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
        jwt {
            verifier(authService.verifier)
            validate { credential ->
                credential.payload.claims.forEach(::println)
                val email = credential.payload.getClaim("email")?.asString() ?: return@validate null
                userService.getUserCredentialsByEmail(email) ?: return@validate null

                JWTPrincipal(credential.payload)
            }
        }
    }

    routing {
        authController(userService, authService)
        eventsController(eventService)
        userController(userService)
    }
}

@KtorExperimentalAPI
fun Application.regularModule() {
    val jwtIssuer = environment.config.property("jwt.domain").getString()
    val jwtSecret = environment.config.property("jwt.secret").getString()

    DatabaseFactory.init()

    val authService = AuthService(jwtIssuer, jwtSecret)
    val eventService = EventServiceImpl()
    val userService = UserServiceImpl()

    module(authService, eventService, userService)
}

@KtorExperimentalAPI
fun Application.testableModule() {
    val jwtIssuer = environment.config.property("jwt.domain").getString()
    val jwtSecret = environment.config.property("jwt.secret").getString()

    val authService = AuthService(jwtIssuer, jwtSecret)
    val eventService = EventServiceImpl()
    val userService = UserServiceMock()

    module(authService, eventService, userService)
}