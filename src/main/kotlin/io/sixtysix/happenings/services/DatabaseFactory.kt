package io.sixtysix.happenings.services

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.sixtysix.happenings.models.EventResponses
import io.sixtysix.happenings.models.Events
import io.sixtysix.happenings.models.Users
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseFactory {

    fun init() {
        Database.connect(hikari())
        transaction {
            SchemaUtils.create(Events)
            SchemaUtils.create(Users)
            SchemaUtils.create(EventResponses)
        }
    }

    private fun hikari(): HikariDataSource {
        val config = HikariConfig()
        config.driverClassName = "org.mariadb.jdbc.Driver"
        config.jdbcUrl = System.getenv("HAPPENINGS_JDBC_URL")
        config.username = System.getenv("HAPPENINGS_USERNAME")
        config.password = System.getenv("HAPPENINGS_PASSWORD")
        config.maximumPoolSize = 3
        return HikariDataSource(config)
    }

    suspend fun <T> dbQuery(block: () -> T): T =
            withContext(Dispatchers.IO) {
                transaction { block() }
            }
}