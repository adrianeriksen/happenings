package io.sixtysix.happenings.services

import io.sixtysix.happenings.forms.NewUserForm
import io.sixtysix.happenings.models.User
import io.sixtysix.happenings.models.UserCredentials
import io.sixtysix.happenings.models.Users
import io.sixtysix.happenings.services.DatabaseFactory.dbQuery
import io.sixtysix.happenings.utils.PasswordUtil
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.joda.time.DateTime

class UserServiceImpl : UserService {

    override suspend fun getUserByEmail(email: String) =
        dbQuery {
            Users.select { Users.email eq email }.mapNotNull { toUser(it) }.singleOrNull()
        }

    override suspend fun getUserCredentialsByEmail(email: String) =
        dbQuery {
            Users.select { Users.email eq email }.mapNotNull { toUserCredentials(it) }.singleOrNull()
        }

    override suspend fun createUser(user: NewUserForm) {
        val currentTime = DateTime.now()

        dbQuery {
            Users.insert {
                it[email] = user.email
                it[hashedPassword] = user.hashedPassword
                it[name] = user.email
                it[createdAt] = currentTime
                it[updatedAt] = currentTime
            }
        }
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