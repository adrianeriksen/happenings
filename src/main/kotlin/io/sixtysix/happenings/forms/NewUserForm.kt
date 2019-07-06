package io.sixtysix.happenings.forms

import io.sixtysix.happenings.utils.PasswordUtil

data class NewUserForm(val email: String, val password: String, val name: String) {

    val hashedPassword: String
        get() = PasswordUtil.hashPassword(password)

    fun isValid(): Boolean {
        if (!email.isEmail()) {
            return false
        }

        if (password.length < 12) {
            return false
        }

        if (name.isBlank()) {
            return false
        }

        return true
    }

    private fun String.isEmail(): Boolean {
        val emailRegex = """(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|"(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21\x23-\x5b\x5d-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])*")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21-\x5a\x53-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])+)\])""".toRegex()
        return emailRegex.matches(this)
    }
}