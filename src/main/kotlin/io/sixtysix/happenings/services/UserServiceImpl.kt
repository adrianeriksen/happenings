package io.sixtysix.happenings.services

import io.sixtysix.happenings.forms.NewUserForm
import io.sixtysix.happenings.models.User
import io.sixtysix.happenings.models.UserCredentials
import io.sixtysix.happenings.models.Users
import io.sixtysix.happenings.services.DatabaseFactory.dbQuery
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import java.time.LocalDateTime

class UserServiceImpl : UserService {

    override suspend fun getUser(id: Int): User? =
        dbQuery {
            Users.select { Users.id eq id }.mapNotNull { it.toUser() }.singleOrNull()
        }

    override suspend fun getUserByEmail(email: String) =
        dbQuery {
            Users.select { Users.email eq email }.mapNotNull { it.toUser() }.singleOrNull()
        }

    override suspend fun getUserCredentialsByEmail(email: String) =
        dbQuery {
            Users.select { Users.email eq email }.mapNotNull { it.toUserCredentials() }.singleOrNull()
        }

    override suspend fun createUser(user: NewUserForm) {
        val currentTime = LocalDateTime.now()

        dbQuery {
            Users.insert {
                it[email] = user.email
                it[hashedPassword] = user.hashedPassword
                it[name] = user.name
                it[createdAt] = currentTime
                it[updatedAt] = currentTime
            }
        }
    }

    private fun ResultRow.toUser(): User =
        User(
            id = this[Users.id],
            email = this[Users.email],
            name = this[Users.name],
            createdAt = this[Users.createdAt],
            updatedAt = this[Users.updatedAt]
        )

    private fun ResultRow.toUserCredentials(): UserCredentials =
        UserCredentials(
            id = this[Users.id],
            email = this[Users.email],
            name = this[Users.name],
            hashedPassword = this[Users.hashedPassword]
        )
}