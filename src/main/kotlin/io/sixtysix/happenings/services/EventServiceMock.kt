package io.sixtysix.happenings.services

import io.sixtysix.happenings.forms.NewEventForm
import io.sixtysix.happenings.models.Event
import org.joda.time.DateTime

class EventServiceMock : EventService {

    override suspend fun getAllEvents(): List<Event> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getEvent(id: Int): Event? {
        if (id == 1) {
            return Event(
                id = 1,
                title = "Example Event",
                where = null,
                description = null,
                startsAt = DateTime.now(),
                endsAt = null,
                createdBy = 1,
                createdAt = DateTime.now(),
                updatedAt = DateTime.now())
        }

        return null
    }

    override suspend fun createEvent(event: NewEventForm, userId: Int) {
        // Do nothing :)
    }

    override suspend fun deleteEvent(id: Int) {
        // Do nothing :)
    }
}