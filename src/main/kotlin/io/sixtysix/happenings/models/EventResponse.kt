package io.sixtysix.happenings.models

import org.joda.time.DateTime

data class EventResponse(val id: Int,
                         val userId: Int,
                         val eventId: Int,
                         val status: EventResponseStatus,
                         val createdAt: DateTime,
                         val updatedAt: DateTime) {

    var userName: String? = null
}