package io.sixtysix.happenings.controllers

import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.route
import io.sixtysix.models.NewEvent
import io.sixtysix.services.EventService

fun Route.eventsController(eventService: EventService) {

    route("/api/events") {

        get("/") {
            val events = eventService.getAllEvents()
            call.respond(events)
        }

        post("/") {
            val event = call.receive<NewEvent>()
            eventService.createEvent(event)
            call.respond(HttpStatusCode.Created)
        }

        get("/{id}") {
            val id = call.parameters["id"]?.toInt()!!
            val event = eventService.getEvent(id)

            if (event == null) call.respond(HttpStatusCode.NotFound)
            else call.respond(event)
        }
    }
}