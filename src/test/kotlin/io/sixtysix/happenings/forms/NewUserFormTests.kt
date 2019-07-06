package io.sixtysix.happenings.forms

import org.junit.jupiter.api.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class NewUserFormTests {

    @Test
    fun `should be valid with correct input`() {
        val userForm = NewUserForm(
            email = "adrian@example.no",
            password = "chocolate crackers",
            name = "Adrian E."
        )

        assertTrue(userForm.isValid())
    }

    @Test
    fun `should be invalid with blank email`() {
        val userForm = NewUserForm(
            email = "",
            password = "chocolate crackers",
            name = "Adrian E."
        )

        assertFalse(userForm.isValid())
    }

    @Test
    fun `should be invalid with short password`() {
        val userForm = NewUserForm(
            email = "adrian@example.no",
            password = "green ghost",
            name = "Adrian E."
        )

        assertFalse(userForm.isValid())
    }

    @Test
    fun `should be invalid with incorrectly formatted email`() {
        val userForm = NewUserForm(
            email = "adrian@example",
            password = "chocolate crackers",
            name = "Adrian E."
        )

        assertFalse(userForm.isValid())
    }

    @Test
    fun `should be invalid with blank name`() {
        val userForm = NewUserForm(
            email = "adrian@example.no",
            password = "chocolate crackers",
            name = ""
        )

        assertFalse(userForm.isValid())
    }
}