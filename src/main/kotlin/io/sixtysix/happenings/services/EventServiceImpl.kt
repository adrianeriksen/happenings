package io.sixtysix.happenings.services

import io.sixtysix.happenings.forms.NewEventForm
import io.sixtysix.happenings.models.Event
import io.sixtysix.happenings.models.Events
import io.sixtysix.happenings.services.DatabaseFactory.dbQuery
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.joda.time.DateTime

class EventServiceImpl : EventService {

    override suspend fun getAllEvents(): List<Event> = dbQuery {
        Events.selectAll().map { toEvent(it) }
    }

    override suspend fun getEvent(id: Int): Event? = dbQuery {
        Events.select { Events.id eq id }.mapNotNull { toEvent(it) }.singleOrNull()
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

    private fun toEvent(row: ResultRow): Event =
            Event(
                id = row[Events.id],
                title = row[Events.title],
                where = row[Events.where],
                description = row[Events.description],
                startsAt = row[Events.startsAt],
                endsAt = row[Events.endsAt],
                createdBy = row[Events.createdBy],
                createdAt = row[Events.createdAt],
                updatedAt = row[Events.updatedAt]
            )
}