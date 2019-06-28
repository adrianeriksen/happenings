package io.sixtysix.happenings.controllers

import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.post
import io.ktor.routing.route
import io.sixtysix.happenings.forms.LoginForm
import io.sixtysix.happenings.services.UserService

fun Route.authController(userService: UserService) {

    route("/api/auth") {

        post("/login") {
            val loginForm = call.receive<LoginForm>()

            val user = userService.getUserByEmail(loginForm.email)

            if (user != null && user.validatePassword(loginForm.password)) {
                call.respond(HttpStatusCode.OK)
            } else {
                call.respond(HttpStatusCode.Forbidden)
            }
        }
    }
}