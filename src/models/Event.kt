package io.sixtysix.models

import org.jetbrains.exposed.sql.Table
import org.joda.time.DateTime

object Events : Table() {
    val id = integer("id").primaryKey().autoIncrement()
    val title = varchar("title", 255)
    val where = varchar("where", 255).nullable()
    val description = text("description").nullable()
    val startsAt = datetime("starts_at")
    val endsAt = datetime("ends_at").nullable()
    val createdAt = datetime("created_on")
    val updatedAt = datetime("updated_on")
}

data class Event(val id: Int,
                 val title: String,
                 val where: String?,
                 val description: String?,
                 val startsAt: DateTime,
                 val endsAt: DateTime?,
                 val createdAt: DateTime,
                 val updatedAt: DateTime)

data class NewEvent(val title: String,
                    val description: String?,
                    val where: String?,
                    val startsAt: DateTime,
                    val endsAt: DateTime?)
