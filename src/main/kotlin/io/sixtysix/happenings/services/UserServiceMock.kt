package io.sixtysix.happenings.services

import io.sixtysix.happenings.forms.NewUserForm
import io.sixtysix.happenings.models.User
import io.sixtysix.happenings.models.UserCredentials
import org.joda.time.DateTime

class UserServiceMock : UserService {

    override suspend fun getUser(id: Int): User? {
        return if (id == 1) {
            val createdAt = DateTime.parse("2019-07-03T09:00")
            User(1, "adrian@example.io", "Adrian Alexander", createdAt, createdAt)
        } else {
            null
        }
    }

    override suspend fun getUserByEmail(email: String): User? {
        val createdAt = DateTime.parse("2019-07-03T09:00")
        return when (email) {
            "adrian@example.io" -> {
                User(1, "adrian@example.io", "Adrian Alexander", createdAt, createdAt)
            }
            "jonas@example.io" -> {
                User(2, "jonas@example.io", "Jonas", createdAt, createdAt)
            }
            else -> null
        }
    }

    // Default password for testing: kitten
    override suspend fun getUserCredentialsByEmail(email: String): UserCredentials? = when (email) {
        "adrian@example.io" -> {
            UserCredentials(1,"adrian@example.io", "Adrian Alexander", "\$argon2i\$v=19\$m=64,t=1,p=1\$Ukh2a2prelBuRkpZN3d6OA\$9NOLgxmi6PY95M/PLw4MjQ")
        }
        "jonas@example.io" -> {
            UserCredentials(2,"jonas@example.io", "Jonas", "\$argon2i\$v=19\$m=64,t=1,p=1\$Ukh2a2prelBuRkpZN3d6OA\$9NOLgxmi6PY95M/PLw4MjQ")
        }
        else -> null
    }

    override suspend fun createUser(user: NewUserForm) {
        // Do nothing :)
    }
}