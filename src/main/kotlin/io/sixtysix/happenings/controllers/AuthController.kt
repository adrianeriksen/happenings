package io.sixtysix.happenings.controllers

import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.route
import io.ktor.sessions.get
import io.ktor.sessions.sessions
import io.ktor.sessions.set
import io.sixtysix.happenings.UserSession
import io.sixtysix.happenings.exceptions.InvalidCredentialsError
import io.sixtysix.happenings.forms.LoginForm
import io.sixtysix.happenings.services.AuthService
import io.sixtysix.happenings.services.UserService

fun Route.authController(userService: UserService, authService: AuthService) {

    route("/api/auth") {

        get("/token") {
            val userSession = call.sessions.get<UserSession>()

            if (userSession != null) {
                val email = authService.getEmail(userSession.token)
                val user = userService.getUserByEmail(email)

                if (user != null) {
                    call.respond(user.toAuthenticatedUser(token = userSession.token))
                    return@get
                }
            } else {
                call.respond(HttpStatusCode.NoContent)
            }
        }

        post("/login") {
            val loginForm = call.receive<LoginForm>()

            val user = userService.getUserCredentialsByEmail(loginForm.email)

            if (user != null && user.validatePassword(loginForm.password)) {
                val token = authService.generateToken(user)
                call.sessions.set(UserSession(token))
                call.respond(user.toAuthenticatedUser(token))
            } else {
                throw InvalidCredentialsError
            }
        }
    }
}