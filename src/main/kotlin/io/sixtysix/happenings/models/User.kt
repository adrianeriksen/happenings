package io.sixtysix.happenings.models

import io.sixtysix.happenings.utils.PasswordUtil
import org.jetbrains.exposed.sql.Table
import org.joda.time.DateTime

object Users : Table() {
    val id = integer("id").primaryKey().autoIncrement()
    val email = varchar("email", 255)
    val encryptedPassword = varchar("encrypted_password", 255)
    val name = varchar("name", 255)
    val createdAt = datetime("created_at")
    val updatedAt = datetime("updated_at")
}

data class User(val id: Int,
                val email: String,
                val encryptedPassword: String,
                val name: String,
                val createdAt: DateTime,
                val updatedAt: DateTime) {

    fun validatePassword(password: String): Boolean =
        PasswordUtil.verifyPassword(this, password)
}