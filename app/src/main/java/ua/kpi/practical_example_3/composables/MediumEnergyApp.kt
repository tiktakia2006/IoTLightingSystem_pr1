package ua.kpi.practical_example_3.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ua.kpi.iot_lighting_system.R

/**
 * Середній рівень: три кнопки режимів у Row, решта елементів у Column.
 * Режим і потужність зберігаються у State.
 */
@Composable
fun MediumEnergyApp() {
    var power by remember { mutableStateOf(0) }
    var mode by remember { mutableStateOf("День") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Система освітлення з IoT — режими",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Row: кнопки режимів в один рядок
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = { mode = "День"; power = 10 }) { Text("День") }
            Button(onClick = { mode = "Вечір"; power = 40 }) { Text("Вечір") }
            Button(onClick = { mode = "Ніч"; power = 5 }) { Text("Ніч") }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Image(
            painter = painterResource(id = R.drawable.smart_lamp),
            contentDescription = "Розумна лампа",
            modifier = Modifier
                .height(180.dp)
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Режим: $mode", fontSize = 18.sp)
        Text(
            text = "Потужність освітлення: $power Вт",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}
