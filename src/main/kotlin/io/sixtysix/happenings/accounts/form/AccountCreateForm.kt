package io.sixtysix.happenings.accounts.form

data class AccountCreateForm(val email: String,
                             val name: String,
                             val password: String)