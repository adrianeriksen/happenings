package io.sixtysix.happenings.controllers

import io.ktor.application.call
import io.ktor.auth.authenticate
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.*
import io.ktor.sessions.get
import io.ktor.sessions.sessions
import io.sixtysix.happenings.UserSession
import io.sixtysix.happenings.forms.EventResponseForm
import io.sixtysix.happenings.forms.NewEventForm
import io.sixtysix.happenings.models.EventResponseStatus
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

            if (event == null) {
                call.respond(HttpStatusCode.NotFound)
                return@get
            }

            eventService.getEventResponses(id).let {
                event.eventResponses = it
            }

            call.respond(event)
        }

        authenticate {
            post("/{id}/response") {
                val principal = call.sessions.get<UserSession>()
                val userId = principal!!.id

                val eventId = call.parameters["id"]?.toInt()!!
                val event = eventService.getEvent(eventId)

                if (event == null) {
                    call.respond(HttpStatusCode.NotFound)
                    return@post
                }

                val eventResponse = call.receive<EventResponseForm>()

                if (eventResponse.status == EventResponseStatus.HOST) {
                    call.respond(HttpStatusCode.Forbidden)
                    return@post
                }

                eventService.updateEventResponse(eventId, userId, eventResponse.status)
                call.respond(HttpStatusCode.NoContent)
            }

            post("/") {
                val principal = call.sessions.get<UserSession>()
                val userId = principal!!.id

                val event = call.receive<NewEventForm>()
                eventService.createEvent(event, userId)
                call.respond(HttpStatusCode.Created)
            }

            delete("/{id}") {
                val principal = call.sessions.get<UserSession>()
                val userId = principal!!.id

                val id = call.parameters["id"]?.toInt()!!
                val event = eventService.getEvent(id)

                if (event == null) {
                    call.respond(HttpStatusCode.NotFound)
                    return@delete
                }

                val hosts = event.eventResponses!!
                    .filter { eventResponse -> eventResponse.status == EventResponseStatus.HOST }
                    .map { eventResponse -> eventResponse.userId  }

                if (!hosts.contains(userId)) {
                    call.respond(HttpStatusCode.Forbidden)
                    return@delete
                }

                eventService.deleteEvent(id)
                call.respond(HttpStatusCode.NoContent)
            }
        }
    }
}