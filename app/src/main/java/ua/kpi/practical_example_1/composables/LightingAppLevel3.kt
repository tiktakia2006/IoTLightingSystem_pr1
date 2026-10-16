package ua.kpi.practical_example_1.composables

import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.random.Random

/**
 * Просунутий рівень: значення змінюються плавно (анімація),
 * дані зберігаються у State, а вигляд береться з MaterialTheme.
 */
@Composable
fun LightingAppLevel3() {
    // Цільові значення (State). При їх зміні запускається анімація.
    var brightness by remember { mutableStateOf(0) }
    var power by remember { mutableStateOf(0) }

    // Плавний перехід від старого значення до нового за 600 мс
    val animatedBrightness by animateIntAsState(
        targetValue = brightness,
        animationSpec = tween(durationMillis = 600),
        label = "BrightnessAnimation"
    )
    val animatedPower by animateIntAsState(
        targetValue = power,
        animationSpec = tween(durationMillis = 600),
        label = "PowerAnimation"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Система освітлення з IoT",
            fontSize = 26.sp,
            color = MaterialTheme.colorScheme.primary // колір із теми
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "Яскравість: $animatedBrightness %",
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.width(20.dp))
            Text(
                text = "Потужність: $animatedPower Вт",
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.onBackground
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = {
            brightness = Random.nextInt(0, 101)
            power = Random.nextInt(0, 61)
        }) {
            Text("Оновити дані")
        }
    }
}
