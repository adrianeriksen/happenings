package io.sixtysix.happenings.accounts

import io.sixtysix.happenings.accounts.form.AccountCreateForm

interface AccountService {

    suspend fun getAccount(id: Int): Account?

    suspend fun createAccount(account: AccountCreateForm): Int
}