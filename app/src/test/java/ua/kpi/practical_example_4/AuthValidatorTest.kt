package ua.kpi.practical_example_4

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import ua.kpi.practical_example_4.forms.AuthValidator

/** Перевірка валідації форм входу та реєстрації. */
class AuthValidatorTest {

    @Test
    fun login_emptyFieldsAreRejected() {
        assertFalse(AuthValidator.validateLogin("", "123").isSuccess)
        assertFalse(AuthValidator.validateLogin("user", "  ").isSuccess)
    }

    @Test
    fun login_filledFieldsAreAccepted() {
        assertTrue(AuthValidator.validateLogin("user", "123").isSuccess)
    }

    @Test
    fun registration_badEmailIsRejected() {
        val r = AuthValidator.validateRegistration("Taras", "not-an-email", "secret1", "secret1")
        assertFalse(r.isSuccess)
        assertEquals("Некоректний email", r.message)
    }

    @Test
    fun registration_shortPasswordIsRejected() {
        val r = AuthValidator.validateRegistration("Taras", "t@ex.com", "123", "123")
        assertFalse(r.isSuccess)
    }

    @Test
    fun registration_mismatchedPasswordsAreRejected() {
        val r = AuthValidator.validateRegistration("Taras", "t@ex.com", "secret1", "secret2")
        assertEquals("Паролі не співпадають", r.message)
    }

    @Test
    fun registration_validDataIsAccepted() {
        val r = AuthValidator.validateRegistration("Taras", "t@ex.com", "secret1", "secret1")
        assertTrue(r.isSuccess)
        assertEquals("Реєстрація успішна!", r.message)
    }
}
