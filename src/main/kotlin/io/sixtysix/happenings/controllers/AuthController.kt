package io.sixtysix.happenings.controllers

import io.ktor.application.call
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.post
import io.ktor.routing.route
import io.sixtysix.happenings.exceptions.InvalidCredentialsError
import io.sixtysix.happenings.forms.LoginForm
import io.sixtysix.happenings.models.Token
import io.sixtysix.happenings.services.AuthService
import io.sixtysix.happenings.services.UserService

fun Route.authController(userService: UserService, authService: AuthService) {

    route("/api/auth") {

        post("/login") {
            val loginForm = call.receive<LoginForm>()

            val user = userService.getUserCredentialsByEmail(loginForm.email)

            if (user != null && user.validatePassword(loginForm.password)) {
                val token = authService.generateToken(user)
                call.respond(Token(token))
            } else {
                throw InvalidCredentialsError
            }
        }
    }
}