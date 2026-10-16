package ua.kpi.practical_example_4.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ua.kpi.practical_example_4.forms.AuthValidator
import ua.kpi.practical_example_4.forms.LoginForm
import ua.kpi.practical_example_4.forms.RegistrationForm

/**
 * Середній рівень: на екрані або форма входу, або форма реєстрації.
 * Перемикання між ними — через State isRegisterMode.
 */
@Composable
fun MediumLoginScreen() {
    var isRegisterMode by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (isRegisterMode) {
            RegistrationForm(
                onRegister = { name, email, password, confirm ->
                    AuthValidator.validateRegistration(name, email, password, confirm)
                },
                onSwitchToLogin = { isRegisterMode = false }
            )
        } else {
            LoginForm(
                onLogin = { login, password -> AuthValidator.validateLogin(login, password) },
                onSwitchToRegister = { isRegisterMode = true }
            )
        }
    }
}
