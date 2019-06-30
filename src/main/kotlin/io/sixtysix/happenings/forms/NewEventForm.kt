package io.sixtysix.happenings.forms

import org.joda.time.DateTime

data class NewEventForm(val title: String,
                        val description: String?,
                        val where: String?,
                        val startsAt: DateTime,
                        val endsAt: DateTime?)