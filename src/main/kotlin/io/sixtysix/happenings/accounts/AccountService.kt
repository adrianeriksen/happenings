package io.sixtysix.happenings.accounts

import io.sixtysix.happenings.accounts.form.AccountCreateForm

interface AccountService {

    suspend fun authenticate(email: String, password: String): Boolean

    suspend fun getAccount(id: Int): Account?

    suspend fun getAccountByEmail(email: String): Account?

    suspend fun createAccount(account: AccountCreateForm): Int
}