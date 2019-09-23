package io.sixtysix.happenings.models

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class UserCredentialsTests {

    private val credential = UserCredentials(
        1,
        "adrian@example.com",
        "Adrian Alexander",
        "\$argon2i\$v=19\$m=1024,t=2,p=2\$Zk9QVTZrWVNUOTg5MENNZA\$rs8PlLzslZpyGrYaFjlWxoHuA1QTePE67fCmY4KNHfM")

    @Test
    fun testShouldValidatePassword() {
        val candidatePassword = "master sunshine"
        val passwordMatches = credential.validatePassword(candidatePassword)

        assertTrue(passwordMatches)
    }

    @Test
    fun testShouldNotValidatePassword() {
        val candidatePassword = "whatever donald "
        val passwordMatches = credential.validatePassword(candidatePassword)

        assertFalse(passwordMatches)
    }
}