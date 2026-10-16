package ua.kpi.practical_example_4.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ua.kpi.practical_example_4.forms.AuthValidator
import ua.kpi.practical_example_4.forms.FormTextField
import ua.kpi.practical_example_4.forms.FormTitle
import ua.kpi.practical_example_4.forms.PasswordField
import ua.kpi.practical_example_4.forms.ValidationResult
import ua.kpi.practical_example_4.forms.ResultMessage

/**
 * Базовий рівень: екран входу з двома полями й однією кнопкою.
 * Перевіряється лише те, що поля не порожні. Усе в Column.
 */
@Composable
fun BasicLoginScreen() {
    var login by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var result by remember { mutableStateOf<ValidationResult?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        FormTitle("Авторизація в системі освітлення")

        FormTextField(login, { login = it }, "Логін")
        Spacer(modifier = Modifier.height(16.dp))

        PasswordField(password, { password = it }, "Пароль")
        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { result = AuthValidator.validateLogin(login, password) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Увійти")
        }

        Spacer(modifier = Modifier.height(16.dp))

        ResultMessage(result)
    }
}
