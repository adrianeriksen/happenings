package io.sixtysix.services

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.sixtysix.models.Events
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
//import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
//import org.joda.time.DateTime

object DatabaseFactory {

    fun init() {
        Database.connect(hikari())
        transaction {
            SchemaUtils.create(Events)
//            Events.insert {
//                it[title] = "Kitten Petting"
//                it[startsAt] = DateTime.now().plusDays(1)
//                it[createdAt] = DateTime.now()
//                it[updatedAt] = DateTime.now()
//            }
//            Events.insert {
//                it[title] = "Milage Run to London"
//                it[startsAt] = DateTime.now().plusDays(2)
//                it[createdAt] = DateTime.now()
//                it[updatedAt] = DateTime.now()
//            }
        }
    }

    private fun hikari(): HikariDataSource {
        val config = HikariConfig()
        config.setDriverClassName("org.mariadb.jdbc.Driver")

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