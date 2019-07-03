package io.sixtysix.happenings.services

import io.sixtysix.happenings.forms.NewUserForm
import io.sixtysix.happenings.models.User
import io.sixtysix.happenings.models.UserCredentials
import org.joda.time.DateTime

class UserServiceMock : UserService {

    override suspend fun getUserByEmail(email: String): User? {
        return if (email == "adrian@example.com") {
            val createdAt = DateTime.parse("2019-07-03T09:00")
            User(1, "adrian@example.io", "Adrian Alexander", createdAt, createdAt)
        } else {
            null
        }
    }

    override suspend fun getUserCredentialsByEmail(email: String): UserCredentials? {
        return if (email == "adrian@example.com") {
            UserCredentials(1, "adrian@example.io", "fa3c8864ba3a9bc022f4225a79143220")
        } else {
            null
        }
    }

    override suspend fun createUser(user: NewUserForm) {
        // Do nothing :)
    }
}