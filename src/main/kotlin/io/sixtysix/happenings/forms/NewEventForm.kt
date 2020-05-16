package io.sixtysix.happenings.forms

import java.time.LocalDateTime

data class NewEventForm(val title: String,
                        val description: String?,
                        val where: String?,
                        val startsAt: LocalDateTime,
                        val endsAt: LocalDateTime?)