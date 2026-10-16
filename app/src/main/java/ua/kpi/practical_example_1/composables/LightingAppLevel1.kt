package ua.kpi.practical_example_1.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Базовий рівень: Compose-функція з текстом, кнопкою та кольором фону.
 * Кнопка поки нічого не робить — це «Hello World» застосунку.
 */
@Composable
fun LightingAppLevel1() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFF8E1)) // теплий світлий фон, як від лампи
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Hello IoT Lighting",
            fontSize = 28.sp,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = { /* на базовому рівні натискання нічого не змінює */ }) {
            Text("Оновити дані")
        }
    }
}
