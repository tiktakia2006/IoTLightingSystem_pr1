package ua.kpi.practical_example_4.forms

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

/**
 * Форма реєстрації: ім'я, email, пароль та його підтвердження.
 *
 * @param onRegister отримує ім'я, email, пароль, підтвердження; повертає результат
 * @param onSwitchToLogin перехід до форми входу
 */
@Composable
fun RegistrationForm(
    onRegister: (String, String, String, String) -> ValidationResult,
    onSwitchToLogin: () -> Unit
) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var result by remember { mutableStateOf<ValidationResult?>(null) }

    FormTitle("Реєстрація в системі освітлення")

    FormTextField(name, { name = it }, "Ім'я")
    Spacer(modifier = Modifier.height(8.dp))

    FormTextField(email, { email = it }, "Email", KeyboardType.Email)
    Spacer(modifier = Modifier.height(8.dp))

    PasswordField(password, { password = it }, "Пароль")
    Spacer(modifier = Modifier.height(8.dp))

    PasswordField(confirmPassword, { confirmPassword = it }, "Підтвердження пароля")
    Spacer(modifier = Modifier.height(16.dp))

    Button(
        onClick = { result = onRegister(name, email, password, confirmPassword) },
        modifier = Modifier.fillMaxWidth()
    ) {
        Text("Зареєструватися")
    }

    Spacer(modifier = Modifier.height(8.dp))

    TextButton(onClick = onSwitchToLogin) {
        Text("Вже є акаунт? Увійти")
    }

    Spacer(modifier = Modifier.height(16.dp))

    ResultMessage(result)
}
