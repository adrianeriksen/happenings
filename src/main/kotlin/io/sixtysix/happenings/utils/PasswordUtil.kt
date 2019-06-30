package io.sixtysix.happenings.utils

import de.mkammerer.argon2.Argon2Factory
import de.mkammerer.argon2.Argon2Helper
import io.sixtysix.happenings.models.UserCredentials

object PasswordUtil {

    private const val TIME_IN_MS = 500L
    private const val MEMORY_COST = 65536
    private const val PARALLELISM = 1

    private var argon2hasher = Argon2Factory.create()

    private var iterations = Argon2Helper.findIterations(argon2hasher, TIME_IN_MS, MEMORY_COST, PARALLELISM)

    fun hashPassword(password: String): String =
        argon2hasher.hash(iterations, MEMORY_COST, PARALLELISM, password)

    fun verifyPassword(user: UserCredentials, password: String): Boolean =
        argon2hasher.verify(user.hashedPassword, password)
}