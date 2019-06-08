package io.sixtysix

import freemarker.cache.ClassTemplateLoader
import io.ktor.application.*
import io.ktor.freemarker.*
import io.ktor.http.HttpStatusCode
import io.ktor.request.receiveParameters
import io.ktor.response.respond
import io.ktor.response.respondRedirect
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.routing
import io.sixtysix.models.NewEvent
import io.sixtysix.services.DatabaseFactory
import io.sixtysix.services.EventService
import org.joda.time.DateTime

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused")
fun Application.module() {
    install(FreeMarker) {
        templateLoader = ClassTemplateLoader(this::class.java.classLoader, "templates")
    }

    DatabaseFactory.init()

    val eventService = EventService()

    routing {
        get("/") {
            val events = eventService.getAllEvents()
            val model = mapOf("events" to events)

            val content = FreeMarkerContent("home.ftl", model, "e")
            call.respond(content)
        }

        get("/events/new") {
            val content = FreeMarkerContent("new.ftl", null, "e")
            call.respond(content)
        }

        get("/events/{id}") {
            val id = call.parameters["id"]?.toInt()!!
            val event = eventService.getEvent(id)

            if (event == null) {
                val content = FreeMarkerContent("shared/404.ftl", null, "e")
                call.respond(HttpStatusCode.NotFound, content)
            } else {
                val model = mapOf("event" to event)
                val content = FreeMarkerContent("show.ftl", model, "e")
                call.respond(content)
            }
        }

        post("/events/new") {
            fun parseDateTime(dateTimeString: String?): DateTime? =
                try { DateTime.parse(dateTimeString) } catch (e: IllegalArgumentException) { null }


            val postParameters = call.receiveParameters()

            val title = postParameters["title"]!!
            val startsAt = parseDateTime(postParameters["starts-at"])!!

            val event = NewEvent(
                title = title,
                where = null,
                description = null,
                startsAt = startsAt,
                endsAt = null)

            eventService.createEvent(event)

            call.respondRedirect("/")
        }
    }
}
