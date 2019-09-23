package io.sixtysix.happenings.models

import org.joda.time.DateTime

data class User(val id: Int,
                val email: String,
                val name: String,
                val createdAt: DateTime,
                val updatedAt: DateTime)