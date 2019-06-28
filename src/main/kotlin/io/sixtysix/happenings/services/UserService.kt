package io.sixtysix.happenings.services

import io.sixtysix.happenings.models.User
import io.sixtysix.happenings.models.Users
import io.sixtysix.happenings.services.DatabaseFactory.dbQuery
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.select

class UserService {

    suspend fun getUserByEmail(email: String): User? =
        dbQuery {
            Users.select { Users.email eq email }.mapNotNull { toUser(it) }.singleOrNull()
        }

    private fun toUser(row: ResultRow): User =
        User(
            id = row[Users.id],
            email = row[Users.email],
            encryptedPassword = row[Users.encryptedPassword],
            name = row[Users.name],
            createdAt = row[Users.createdAt],
            updatedAt = row[Users.updatedAt]
        )
}