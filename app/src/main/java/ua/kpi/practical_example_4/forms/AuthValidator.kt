package ua.kpi.practical_example_4.forms

/** Результат перевірки: успіх або текст помилки для користувача. */
data class ValidationResult(val isSuccess: Boolean, val message: String)

/**
 * Бізнес-логіка форм входу та реєстрації.
 * Винесена з UI, щоб її можна було тестувати без Compose.
 */
object AuthValidator {

    private const val MIN_PASSWORD_LENGTH = 6
    private val EMAIL_REGEX = Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")

    /** Перевірка форми входу. */
    fun validateLogin(login: String, password: String): ValidationResult = when {
        login.isBlank() || password.isBlank() ->
            ValidationResult(false, "Заповніть усі поля")
        else ->
            ValidationResult(true, "Авторизація успішна!")
    }

    /** Перевірка форми реєстрації. */
    fun validateRegistration(
        name: String,
        email: String,
        password: String,
        confirmPassword: String
    ): ValidationResult = when {
        name.isBlank() || email.isBlank() || password.isBlank() || confirmPassword.isBlank() ->
            ValidationResult(false, "Заповніть усі поля")
        !EMAIL_REGEX.matches(email.trim()) ->
            ValidationResult(false, "Некоректний email")
        password.length < MIN_PASSWORD_LENGTH ->
            ValidationResult(false, "Пароль має містити щонайменше $MIN_PASSWORD_LENGTH символів")
        password != confirmPassword ->
            ValidationResult(false, "Паролі не співпадають")
        else ->
            ValidationResult(true, "Реєстрація успішна!")
    }
}
