package io.sixtysix.happenings.services

import io.sixtysix.happenings.forms.NewEventForm
import io.sixtysix.happenings.models.Event
import org.joda.time.DateTime

val bikesheddingEvent = Event(
    id = 1,
    title = "Technical bikeshedding with Adrian",
    where = "The Bank, Oslo",
    description = null,
    startsAt = DateTime.parse("2019-08-08T07:30:00.000"),
    endsAt = DateTime.parse("2019-08-08T15:30:00"),
    createdBy = 1,
    createdAt = DateTime.parse("2019-08-05T22:15:00"),
    updatedAt = DateTime.parse("2019-08-05T22:15:00")
)

val chessEvent = Event(
    id = 2,
    title = "Chess mastery with Jonas",
    where = "The Good Knight, Oslo",
    description = null,
    startsAt = DateTime.parse("2019-08-09T17:30:00"),
    endsAt = DateTime.parse("2019-08-10T01:00:00"),
    createdBy = 2,
    createdAt = DateTime.parse("2019-07-01T18:00:00"),
    updatedAt = DateTime.parse("2019-07-20T09:45:00")
)

val musicEvent = Event(
    id = 3,
    title = "Partyfest",
    where = "Grünerløkka, Oslo",
    description = null,
    startsAt = DateTime.parse("2019-08-10T01:15:00"),
    endsAt = DateTime.parse("2019-08-10T02:00:00"),
    createdBy = 1,
    createdAt = DateTime.parse("2019-08-07T18:25:00"),
    updatedAt = DateTime.parse("2019-08-07T18:25:00")
)

class EventServiceMock : EventService {

    override suspend fun getAllEvents(): List<Event> =
        listOf(bikesheddingEvent, chessEvent, musicEvent)

    override suspend fun getEvent(id: Int): Event? = when (id) {
        1 -> bikesheddingEvent
        2 -> chessEvent
        3 -> musicEvent
        else -> null
    }

    override suspend fun createEvent(event: NewEventForm, userId: Int) {
        // Do nothing :)
    }

    override suspend fun deleteEvent(id: Int) {
        // Do nothing :)
    }
}