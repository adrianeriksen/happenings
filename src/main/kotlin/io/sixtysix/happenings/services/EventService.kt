package io.sixtysix.happenings.services

import io.sixtysix.happenings.forms.NewEventForm
import io.sixtysix.happenings.models.Event
import io.sixtysix.happenings.models.EventResponse

interface EventService {

    suspend fun getAllEvents(): List<Event>

    suspend fun getEvent(id: Int): Event?

    suspend fun getEventResponses(eventId: Int): List<EventResponse>

    suspend fun createEvent(event: NewEventForm, userId: Int)

    suspend fun deleteEvent(id: Int)
}