package io.sixtysix.happenings.services

import io.sixtysix.happenings.forms.NewEventForm
import io.sixtysix.happenings.models.Event

class EventServiceMock : EventService {

    override suspend fun getAllEvents(): List<Event> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getEvent(id: Int): Event? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun createEvent(event: NewEventForm, userId: Int) {
        // Do nothing :)
    }
}