package io.sixtysix.happenings.controllers

import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.post
import io.ktor.routing.route
import io.sixtysix.happenings.forms.NewUserForm
import io.sixtysix.happenings.services.UserService

fun Route.userController(userService: UserService) {

    route("/api/user") {

        post("/") {
            val userForm = call.receive<NewUserForm>()

            if (userForm.isValid()) {
                userService.createUser(userForm)
                call.respond(HttpStatusCode.Created)
            } else {
                call.respond(HttpStatusCode.BadRequest)
            }
        }
    }
}