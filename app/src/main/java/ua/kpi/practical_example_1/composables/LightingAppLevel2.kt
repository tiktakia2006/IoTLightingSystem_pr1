package ua.kpi.practical_example_1.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.random.Random

/**
 * Середній рівень: змінні дані. Після натискання кнопки
 * яскравість і споживання змінюються випадково (імітація IoT-датчиків).
 * Елементи скомпоновані через Column і Row.
 */
@Composable
fun LightingAppLevel2() {
    // Стан: яскравість лампи у відсотках та споживання у ватах
    var brightness by remember { mutableStateOf(0) }
    var power by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFFDE7))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Система освітлення з IoT",
            fontSize = 24.sp,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Row: два показники в один рядок
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Яскравість: $brightness %",
                fontSize = 18.sp,
                color = Color.DarkGray
            )
            Spacer(modifier = Modifier.width(24.dp))
            Text(
                text = "Потужність: $power Вт",
                fontSize = 18.sp,
                color = Color.DarkGray
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = {
            // Нові випадкові значення: яскравість 0–100 %, потужність 0–60 Вт
            brightness = Random.nextInt(0, 101)
            power = Random.nextInt(0, 61)
        }) {
            Text("Оновити дані")
        }
    }
}
