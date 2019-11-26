package io.sixtysix.happenings.services

import io.sixtysix.happenings.forms.NewEventForm
import io.sixtysix.happenings.models.*
import io.sixtysix.happenings.services.DatabaseFactory.dbQuery
import org.jetbrains.exposed.sql.*
import org.joda.time.DateTime

class EventServiceImpl : EventService {

    override suspend fun getAllEvents(): List<Event> = dbQuery {
        Events.selectAll().map { it.toEvent() }
    }

    override suspend fun getEvent(id: Int): Event? = dbQuery {
        Events.select { Events.id eq id }.mapNotNull { it.toEvent() }.singleOrNull()
    }

    override suspend fun getEventResponses(eventId: Int): List<EventResponse> = dbQuery {
        (EventResponses innerJoin Users)
            .slice(EventResponses.id, EventResponses.user, Users.name, EventResponses.status, EventResponses.createdAt, EventResponses.updatedAt)
            .select { EventResponses.event eq eventId }
            .map { it.toEventResponse(eventId) }
    }

    override suspend fun createEvent(event: NewEventForm, userId: Int) {
        val currentTime = DateTime.now()

        dbQuery {
            Events.insert {
                it[title] = event.title
                it[where] = event.where
                it[description] = event.description
                it[startsAt] = event.startsAt
                it[endsAt] = event.endsAt
                it[createdBy] = userId
                it[createdAt] = currentTime
                it[updatedAt] = currentTime
            }
        }
    }

    override suspend fun deleteEvent(id: Int) {
        dbQuery {
            Events.deleteWhere { Events.id eq id }
        }
    }

    private fun ResultRow.toEvent(): Event =
            Event(
                id = this[Events.id],
                title = this[Events.title],
                where = this[Events.where],
                description = this[Events.description],
                startsAt = this[Events.startsAt],
                endsAt = this[Events.endsAt],
                createdBy = this[Events.createdBy],
                createdAt = this[Events.createdAt],
                updatedAt = this[Events.updatedAt]
            )

    private fun ResultRow.toEventResponse(eventId: Int): EventResponse {
        val eventResponse = EventResponse(
            id = this[EventResponses.id],
            userId = this[EventResponses.user],
            eventId = eventId,
            status = EventResponseStatus.valueOf(this[EventResponses.status]),
            createdAt = this[EventResponses.createdAt],
            updatedAt = this[EventResponses.updatedAt]
        )

        eventResponse.userName = this[Users.name]

        return eventResponse
    }
}