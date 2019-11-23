package io.sixtysix.happenings.models

import org.joda.time.DateTime

data class EventResponse(val id: Int,
                         val user_id: Int,
                         val event_id: Int,
                         val status: EventResponseStatus,
                         val createdAt: DateTime,
                         val updatedAt: DateTime)