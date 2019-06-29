package io.sixtysix.happenings.models

import io.sixtysix.happenings.utils.PasswordUtil

data class UserCredentials(val id: Int,
                           val email: String,
                           val encryptedPassword: String) {

    fun validatePassword(password: String): Boolean =
        PasswordUtil.verifyPassword(this, password)
}