package io.sixtysix.happenings.controllers

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import io.ktor.http.*
import io.ktor.server.testing.handleRequest
import io.ktor.server.testing.setBody
import io.sixtysix.happenings.forms.EventResponseForm
import io.sixtysix.happenings.forms.LoginForm
import io.sixtysix.happenings.models.Event
import io.sixtysix.happenings.models.EventResponseStatus
import io.sixtysix.happenings.utils.DateTimeAdapter
import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import kotlin.test.assertEquals

class EventsControllerTest : ControllerTest {

    private val gson = Gson()

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
        val form = LoginForm("adrian@example.io", "kitten")
        val sessionCookie = fetchSessionCookie(form)

        handleRequest(HttpMethod.Delete, "/api/events/42") {
            addHeader(HttpHeaders.ContentType, ContentType.Application.Json.toString())
            addHeader(HttpHeaders.Cookie, "$SESSION_COOKIE_NAME=${sessionCookie.value.encodeURLParameter()}")
        }.apply {
            assertEquals(HttpStatusCode.NotFound, response.status())
        }
    }

    @Test
    fun `Return 403 Forbidden when authenticated user is not owner`() = testApplication {
        val form = LoginForm("adrian@example.io", "kitten")
        val sessionCookie = fetchSessionCookie(form)

        handleRequest(HttpMethod.Delete, "/api/events/2") {
            addHeader(HttpHeaders.ContentType, ContentType.Application.Json.toString())
            addHeader(HttpHeaders.Cookie, "$SESSION_COOKIE_NAME=${sessionCookie.value.encodeURLParameter()}")
        }.apply {
            assertEquals(HttpStatusCode.Forbidden, response.status())
        }
    }

    @Test
    fun `Return 204 No Content when delete was successful`() = testApplication {
        val form = LoginForm("adrian@example.io", "kitten")
        val sessionCookie = fetchSessionCookie(form)

        handleRequest(HttpMethod.Delete, "/api/events/1") {
            addHeader(HttpHeaders.ContentType, ContentType.Application.Json.toString())
            addHeader(HttpHeaders.Cookie, "$SESSION_COOKIE_NAME=${sessionCookie.value.encodeURLParameter()}")
        }.apply {
            assertEquals(HttpStatusCode.NoContent, response.status())
        }
    }

    @Test
    fun `Return 403 Forbidden when user escalates privileges to host`() = testApplication {
        val form = LoginForm("jonas@example.io", "kitten")
        val sessionCookie = fetchSessionCookie(form)

        val payload = EventResponseForm(EventResponseStatus.HOST)

        handleRequest(HttpMethod.Post, "/api/events/1/response") {
            addHeader(HttpHeaders.ContentType, ContentType.Application.Json.toString())
            addHeader(HttpHeaders.Cookie, "$SESSION_COOKIE_NAME=${sessionCookie.value.encodeURLParameter()}")
            setBody(gson.toJson(payload))
        }.apply {
            assertEquals(HttpStatusCode.Forbidden, response.status())
        }
    }

    private fun getGsonInstance() =
        GsonBuilder().registerTypeAdapter(LocalDateTime::class.java, DateTimeAdapter()).create()
}