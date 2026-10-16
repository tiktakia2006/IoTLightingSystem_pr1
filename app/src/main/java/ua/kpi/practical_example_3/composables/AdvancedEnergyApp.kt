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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ua.kpi.iot_lighting_system.R

/**
 * Просунутий рівень: UI розбитий на кілька composable-функцій
 * (ModeButton, InfoCard), стан живе у State (remember + mutableStateOf).
 */
@Composable
fun AdvancedEnergyApp() {
    var power by remember { mutableStateOf(0) }
    var mode by remember { mutableStateOf("День") }
    var comments by remember { mutableStateOf("Все стабільно") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Система освітлення з IoT — панель керування",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Кожна кнопка передає у батьківський State режим, потужність і коментар
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            ModeButton("День", 10) {
                mode = it.first
                power = it.second
                comments = "Природного світла достатньо"
            }
            ModeButton("Вечір", 40) {
                mode = it.first
                power = it.second
                comments = "Комфортне освітлення"
            }
            ModeButton("Ніч", 5) {
                mode = it.first
                power = it.second
                comments = "Нічний режим, мінімум світла"
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Image(
            painter = painterResource(id = R.drawable.smart_lamp),
            contentDescription = "Розумна лампа",
            modifier = Modifier
                .height(160.dp)
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        InfoCard(power = power, mode = mode, comments = comments)
    }
}

/** Кнопка режиму: при натисканні повертає пару «назва режиму → потужність». */
@Composable
fun ModeButton(label: String, value: Int, onClick: (Pair<String, Int>) -> Unit) {
    Button(onClick = { onClick(label to value) }) {
        Text(text = label)
    }
}

/** Інформаційна картка: режим, потужність і коментар. */
@Composable
fun InfoCard(power: Int, mode: String, comments: String) {
    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF3CD))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Режим: $mode", fontSize = 18.sp, color = Color.Black)
            Text(
                "Потужність освітлення: $power Вт",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Text("Коментар: $comments", fontSize = 16.sp, color = Color.DarkGray)
        }
    }
}
