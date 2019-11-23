package io.sixtysix.happenings.models

import org.jetbrains.exposed.sql.Table

object Events : Table() {
    val id = integer("id").autoIncrement().primaryKey()
    val title = varchar("title", 255)
    val where = varchar("where", 255).nullable()
    val description = text("description").nullable()
    val startsAt = datetime("starts_at")
    val endsAt = datetime("ends_at").nullable()
    val createdBy = integer("created_by_user_id") references Users.id
    val createdAt = datetime("created_at")
    val updatedAt = datetime("updated_at")
}

object Users : Table() {
    val id = integer("id").autoIncrement().primaryKey()
    val email = varchar("email", 255).uniqueIndex()
    val hashedPassword = varchar("hashed_password", 255)
    val name = varchar("name", 255)
    val createdAt = datetime("created_at")
    val updatedAt = datetime("updated_at")
}

object EventResponses : Table() {
    val id = integer("id").autoIncrement().primaryKey()
    val user = integer("user_id") references Users.id
    val event = integer("event_id") references Events.id
    val status = varchar("status", 32)
    val createdAt = datetime("created_at")
    val updatedAt = datetime("updated_at")

    init {
        index(true, user, event)
    }
}