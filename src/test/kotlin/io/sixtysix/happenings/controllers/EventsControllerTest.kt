package io.sixtysix.happenings.controllers

import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.server.testing.handleRequest
import io.sixtysix.happenings.models.Event
import io.sixtysix.happenings.utils.DateTimeAdapter
import org.joda.time.DateTime
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class EventsControllerTest : ControllerTest {

    @Test
    fun `Return all events`() = testApplication {
        handleRequest(HttpMethod.Get, "/api/events") {
            addHeader(HttpHeaders.ContentType, ContentType.Application.Json.toString())
        }.apply {
            val listType = object : TypeToken<List<Event>>() {}.type;
            val events = getGsonInstance().fromJson<List<Event>>(response.content, listType)

            assertEquals(HttpStatusCode.OK, response.status())
            assertEquals(3, events.size)
        }
    }

    @Test
    fun `Return single event`() = testApplication {
        handleRequest(HttpMethod.Get, "/api/events/1") {
            addHeader(HttpHeaders.ContentType, ContentType.Application.Json.toString())
        }.apply {
            val event = getGsonInstance().fromJson(response.content, Event::class.java)

            assertEquals(HttpStatusCode.OK, response.status())
            assertEquals(1, event.id)
        }
    }

    @Test
    fun `Return 404 Not Found when single event does not exist`() = testApplication {
        handleRequest(HttpMethod.Get, "/api/events/42") {
            addHeader(HttpHeaders.ContentType, ContentType.Application.Json.toString())
        }.apply {
            assertEquals(HttpStatusCode.NotFound, response.status())
        }
    }

    @Test
    fun `Return 401 Unauthorized when unauthenticated`() = testApplication {
        handleRequest(HttpMethod.Delete, "/api/events/1") {
            addHeader(HttpHeaders.ContentType, ContentType.Application.Json.toString())
        }.apply {
            assertEquals(HttpStatusCode.Unauthorized, response.status())
        }
    }

    @Test
    fun `Return 404 Not Found when provided invalid id`() = testApplication {
        handleRequest(HttpMethod.Delete, "/api/events/42") {
            addHeader(HttpHeaders.ContentType, ContentType.Application.Json.toString())
        }.apply {
            assertEquals(HttpStatusCode.NotFound, response.status())
        }
    }

    @Test
    fun `Return 403 Forbidden when authenticated user is not owner`() = testApplication {
        handleRequest(HttpMethod.Delete, "/api/events/2") {
            addHeader(HttpHeaders.ContentType, ContentType.Application.Json.toString())
        }.apply {
            assertEquals(HttpStatusCode.Forbidden, response.status())
        }
    }

    @Test
    fun `Return 204 No Content when delete was successful`() = testApplication {
        handleRequest(HttpMethod.Delete, "/api/events/1") {
            addHeader(HttpHeaders.ContentType, ContentType.Application.Json.toString())
        }.apply {
            assertEquals(HttpStatusCode.NoContent, response.status())
        }
    }

    private fun getGsonInstance() =
        GsonBuilder().registerTypeAdapter(DateTime::class.java, DateTimeAdapter()).create()
}