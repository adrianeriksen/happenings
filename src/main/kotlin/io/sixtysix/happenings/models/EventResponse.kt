package io.sixtysix.happenings.models

import java.time.LocalDateTime

data class EventResponse(val id: Int,
                         val userId: Int,
                         val eventId: Int,
                         val status: EventResponseStatus,
                         val createdAt: LocalDateTime,
                         val updatedAt: LocalDateTime) {

    var userName: String? = null
}