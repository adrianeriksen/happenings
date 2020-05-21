package io.sixtysix.happenings.accounts

import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.post
import io.ktor.routing.route
import io.sixtysix.happenings.accounts.form.AccountCreateForm

fun Route.accountsResource(accountService: AccountService) {

    route("/api/accounts") {

        post("/") {
            val accountCreateForm = call.receive<AccountCreateForm>()

            val accountId = accountService.createAccount(accountCreateForm)
            val account = accountService.getAccount(accountId)!!

            call.respond(HttpStatusCode.Created, account)
        }
    }
}