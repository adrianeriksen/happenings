package io.sixtysix.services

import io.sixtysix.models.Event
import io.sixtysix.models.Events
import io.sixtysix.models.NewEvent
import io.sixtysix.services.DatabaseFactory.dbQuery
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.joda.time.DateTime

class EventService {

    suspend fun getAllEvents(): List<Event> = dbQuery {
        Events.selectAll().map { toEvent(it) }
    }

    suspend fun getEvent(id: Int): Event? = dbQuery {
        Events.select { Events.id eq id }.mapNotNull { toEvent(it) }.singleOrNull()
    }

    suspend fun createEvent(event: NewEvent) {
        val currentTime = DateTime.now()

        dbQuery {
            Events.insert {
                it[title] = event.title
                it[where] = event.where
                it[description] = event.description
                it[startsAt] = event.startsAt
                it[endsAt] = event.endsAt
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
                createdAt = row[Events.createdAt],
                updatedAt = row[Events.updatedAt]
            )
}