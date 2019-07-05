package io.sixtysix.happenings.services

import io.sixtysix.happenings.forms.NewEventForm
import io.sixtysix.happenings.models.Event

interface EventService {

    suspend fun getAllEvents(): List<Event>

    suspend fun getEvent(id: Int): Event?

    suspend fun createEvent(event: NewEventForm, userId: Int): Unit
}