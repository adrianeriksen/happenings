package io.sixtysix.happenings.utils

import de.mkammerer.argon2.Argon2Factory
import io.sixtysix.happenings.models.UserCredentials

object PasswordUtil {

    fun verifyPassword(user: UserCredentials, password: String): Boolean {
        val argon2 = Argon2Factory.create()

        argon2.run {
            val isValid = verify(user.hashedPassword, password)

            wipeArray(password.toCharArray())
            return isValid
        }
    }

}