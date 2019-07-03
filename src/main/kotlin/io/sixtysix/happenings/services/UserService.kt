package io.sixtysix.happenings.services

import io.sixtysix.happenings.forms.NewUserForm
import io.sixtysix.happenings.models.User
import io.sixtysix.happenings.models.UserCredentials

interface UserService {

    suspend fun getUserByEmail(email: String): User?

    suspend fun getUserCredentialsByEmail(email: String): UserCredentials?

    suspend fun createUser(user: NewUserForm)
}