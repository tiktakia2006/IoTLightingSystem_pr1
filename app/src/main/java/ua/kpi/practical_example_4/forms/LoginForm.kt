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
import androidx.compose.ui.unit.dp

/**
 * Форма входу. Сама нічого не перевіряє — віддає введені дані
 * назовні через onLogin, а результат показує в ResultMessage.
 *
 * @param onLogin отримує логін і пароль, повертає результат перевірки
 * @param onSwitchToRegister перехід до форми реєстрації
 */
@Composable
fun LoginForm(
    onLogin: (String, String) -> ValidationResult,
    onSwitchToRegister: () -> Unit
) {
    var login by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var result by remember { mutableStateOf<ValidationResult?>(null) }

    FormTitle("Вхід у систему освітлення")

    FormTextField(login, { login = it }, "Логін")
    Spacer(modifier = Modifier.height(8.dp))

    PasswordField(password, { password = it }, "Пароль")
    Spacer(modifier = Modifier.height(16.dp))

    Button(
        onClick = { result = onLogin(login, password) },
        modifier = Modifier.fillMaxWidth()
    ) {
        Text("Увійти")
    }

    Spacer(modifier = Modifier.height(8.dp))

    TextButton(onClick = onSwitchToRegister) {
        Text("Ще немає акаунту? Зареєструватися")
    }

    Spacer(modifier = Modifier.height(16.dp))

    ResultMessage(result)
}
