package io.sixtysix.happenings.models

import java.time.LocalDateTime

data class User(val id: Int,
                val email: String,
                val name: String,
                val createdAt: LocalDateTime,
                val updatedAt: LocalDateTime)