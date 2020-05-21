package io.sixtysix.happenings.accounts

import io.sixtysix.happenings.accounts.form.AccountCreateForm
import io.sixtysix.happenings.services.DatabaseFactory.dbQuery
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select

class AccountServiceImpl : AccountService {

    override suspend fun getAccount(id: Int): Account? =
        dbQuery {
            Accounts.select { Accounts.id eq id }.mapNotNull { it.toAccount() }.singleOrNull()
        }

    override suspend fun createAccount(account: AccountCreateForm): Int =
        dbQuery {
            Accounts.insert {
                it[email] = account.email
                it[name] = account.name
                it[hashedPassword] = account.password
            } get Accounts.id
        }

    private fun ResultRow.toAccount(): Account =
        Account(
            id = this[Accounts.id],
            email = this[Accounts.email],
            name = this[Accounts.name],
            hashedPassword = this[Accounts.hashedPassword]
        )
}