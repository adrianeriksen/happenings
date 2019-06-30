package io.sixtysix.happenings.forms

import io.sixtysix.happenings.utils.PasswordUtil

data class NewUserForm(val email: String, val password: String, val name: String) {

    val hashedPassword: String
        get() = PasswordUtil.hashPassword(password)
}