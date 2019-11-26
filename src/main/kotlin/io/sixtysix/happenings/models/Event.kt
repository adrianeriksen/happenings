package io.sixtysix.happenings.models

import org.joda.time.DateTime

data class Event(val id: Int,
                 val title: String,
                 val where: String?,
                 val description: String?,
                 val startsAt: DateTime,
                 val endsAt: DateTime?,
                 val createdBy: Int,
                 val createdAt: DateTime,
                 val updatedAt: DateTime) {

    var eventResponses: List<EventResponse>? = null
}