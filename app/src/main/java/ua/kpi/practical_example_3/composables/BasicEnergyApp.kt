package ua.kpi.practical_example_3.composables

import androidx.compose.foundation.Image
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ua.kpi.iot_lighting_system.R

/**
 * Базовий рівень: заголовок, зображення лампи та одна кнопка,
 * яка змінює текст при натисканні.
 */
@Composable
fun BasicEnergyApp() {
    // Текст, що змінюється після натискання кнопки
    var statusText by remember { mutableStateOf("Поточна потужність: 0 Вт") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Система освітлення з IoT",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        Image(
            painter = painterResource(id = R.drawable.smart_lamp),
            contentDescription = "Розумна лампа",
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = statusText, fontSize = 18.sp)

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { statusText = "Прогнозована потужність: 40 Вт" }) {
            Text(text = "Оновити прогноз")
        }
    }
}
