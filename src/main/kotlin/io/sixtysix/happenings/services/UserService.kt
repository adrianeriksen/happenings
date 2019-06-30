package io.sixtysix.happenings.services

import io.sixtysix.happenings.models.User
import io.sixtysix.happenings.models.UserCredentials
import io.sixtysix.happenings.models.Users
import io.sixtysix.happenings.services.DatabaseFactory.dbQuery
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.select

class UserService {

    suspend fun getUserByEmail(email: String): User? =
        dbQuery {
            Users.select { Users.email eq email }.mapNotNull { toUser(it) }.singleOrNull()
        }

    suspend fun getUserCredentialsByEmail(email: String): UserCredentials? =
        dbQuery {
            Users.select { Users.email eq email }.mapNotNull { toUserCredentials(it) }.singleOrNull()
        }

    private fun toUser(row: ResultRow): User =
        User(
            id = row[Users.id],
            email = row[Users.email],
            name = row[Users.name],
            createdAt = row[Users.createdAt],
            updatedAt = row[Users.updatedAt]
        )

    private fun toUserCredentials(row: ResultRow): UserCredentials =
        UserCredentials(
            id = row[Users.id],
            email = row[Users.email],
            hashedPassword = row[Users.hashedPassword]
        )
}