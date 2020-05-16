package io.sixtysix.happenings.models

import java.time.LocalDateTime

data class Event(val id: Int,
                 val title: String,
                 val where: String?,
                 val description: String?,
                 val startsAt: LocalDateTime,
                 val endsAt: LocalDateTime?,
                 val createdAt: LocalDateTime,
                 val updatedAt: LocalDateTime) {

    var eventResponses: List<EventResponse>? = null
}