package ua.kpi.practical_example_4.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ua.kpi.practical_example_4.forms.AuthValidator
import ua.kpi.practical_example_4.forms.FormTextField
import ua.kpi.practical_example_4.forms.FormTitle
import ua.kpi.practical_example_4.forms.PasswordField
import ua.kpi.practical_example_4.forms.ResultMessage
import ua.kpi.practical_example_4.forms.ValidationResult

/**
 * Просунутий рівень:
 *  - UI розбитий на окремі composable (FormTitle, FormTextField, PasswordField, ResultMessage);
 *  - валідація в AuthValidator (бізнес-логіка окремо від UI);
 *  - під час «запиту до сервера» показується індикатор завантаження
 *    (затримка імітується корутиною), кнопки на цей час блокуються;
 *  - layout прокручується, тож форма влізає навіть на малий екран.
 */
@Composable
fun AdvancedLoginScreen() {
    // --- State екрана ---
    var isRegisterMode by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(false) }
    var result by remember { mutableStateOf<ValidationResult?>(null) }

    // --- State полів (спільний для обох режимів) ---
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var login by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    // Корутина для імітації мережевого запиту
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        FormTitle(
            if (isRegisterMode) "Реєстрація в системі освітлення"
            else "Вхід у систему освітлення"
        )

        if (isRegisterMode) {
            FormTextField(name, { name = it }, "Ім'я")
            Spacer(modifier = Modifier.height(8.dp))
            FormTextField(email, { email = it }, "Email", KeyboardType.Email)
        } else {
            FormTextField(login, { login = it }, "Логін")
        }
        Spacer(modifier = Modifier.height(8.dp))

        PasswordField(password, { password = it }, "Пароль")

        if (isRegisterMode) {
            Spacer(modifier = Modifier.height(8.dp))
            PasswordField(confirmPassword, { confirmPassword = it }, "Підтвердження пароля")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                // Запускаємо «запит»: показуємо індикатор, чекаємо, перевіряємо дані
                scope.launch {
                    isLoading = true
                    result = null
                    delay(1200) // імітація відповіді сервера
                    result = if (isRegisterMode) {
                        AuthValidator.validateRegistration(name, email, password, confirmPassword)
                    } else {
                        AuthValidator.validateLogin(login, password)
                    }
                    isLoading = false
                }
            },
            enabled = !isLoading, // поки йде запит — повторно натиснути не можна
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(if (isRegisterMode) "Зареєструватися" else "Увійти")
        }

        Spacer(modifier = Modifier.height(8.dp))

        TextButton(
            onClick = {
                isRegisterMode = !isRegisterMode
                result = null // старе повідомлення на іншій формі не потрібне
            },
            enabled = !isLoading
        ) {
            Text(
                if (isRegisterMode) "Вже є акаунт? Увійти"
                else "Ще немає акаунту? Зареєструватися"
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (isLoading) {
            CircularProgressIndicator()
        } else {
            ResultMessage(result)
        }
    }
}
