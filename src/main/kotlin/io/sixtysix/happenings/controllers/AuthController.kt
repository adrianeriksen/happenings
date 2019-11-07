package io.sixtysix.happenings.controllers

import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.route
import io.ktor.sessions.clear
import io.ktor.sessions.get
import io.ktor.sessions.sessions
import io.ktor.sessions.set
import io.sixtysix.happenings.UserSession
import io.sixtysix.happenings.exceptions.InvalidCredentialsError
import io.sixtysix.happenings.forms.LoginForm
import io.sixtysix.happenings.services.UserService

fun Route.authController(userService: UserService) {

    route("/api/auth") {

        get("/user") {
            val userSession = call.sessions.get<UserSession>()

            if (userSession != null) {
                userService.getUser(userSession.id)?.let {
                    call.respond(it)
                    return@get
                }
            }

            call.respond(HttpStatusCode.NoContent)
        }

        post("/login") {
            val loginForm = call.receive<LoginForm>()

            val user = userService.getUserCredentialsByEmail(loginForm.email)

            if (user != null && user.validatePassword(loginForm.password)) {
                call.sessions.set(UserSession(user.id))
                call.respond(user)
            } else {
                throw InvalidCredentialsError
            }
        }

        post("/logout") {
            call.sessions.clear<UserSession>()
            call.respond(HttpStatusCode.NoContent)
        }
    }
}