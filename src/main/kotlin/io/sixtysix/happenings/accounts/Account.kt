package io.sixtysix.happenings.accounts

import org.jetbrains.exposed.sql.Table

object Accounts : Table() {
    val id = integer("id").autoIncrement()
    val email = varchar("email", length = 200).uniqueIndex()
    val name = varchar("name", length = 200)
    val hashedPassword = varchar("hashed_password", length = 200)

    override val primaryKey: PrimaryKey?
        get() = PrimaryKey(id, name = "pk_user_id")
}

data class Account(val id: Int,
                   val email: String,
                   val name: String,
                   val hashedPassword: String)