package io.sixtysix.happenings.controllers

import io.ktor.application.call
import io.ktor.auth.authenticate
import io.ktor.auth.authentication
import io.ktor.auth.jwt.JWTPrincipal
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.route
import io.sixtysix.happenings.forms.NewEventForm
import io.sixtysix.happenings.services.EventService

fun Route.eventsController(eventService: EventService) {

    route("/api/events") {

        get("/") {
            val events = eventService.getAllEvents()
            call.respond(events)
        }

        get("/{id}") {
            val id = call.parameters["id"]?.toInt()!!
            val event = eventService.getEvent(id)

            if (event == null) call.respond(HttpStatusCode.NotFound)
            else call.respond(event)
        }

        authenticate {
            post("/") {
                val principal = call.authentication.principal<JWTPrincipal>()
                val userId = principal!!.payload.getClaim("id").asInt()

                val event = call.receive<NewEventForm>()
                eventService.createEvent(event, userId)
                call.respond(HttpStatusCode.Created)
            }
        }
    }
}