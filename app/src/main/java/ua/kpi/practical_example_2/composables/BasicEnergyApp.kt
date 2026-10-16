package ua.kpi.practical_example_2.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ua.kpi.practical_example_2.data.LightingCalculator
import ua.kpi.practical_example_2.data.toDoubleOrZero

/**
 * Базовий рівень: один параметр — номінальна потужність лампи.
 * Результат: споживання за годину роботи на повну яскравість.
 */
@Composable
fun BasicEnergyApp() {
    // Значення поля вводу (рядок) та результат обчислення
    var nominalPower by remember { mutableStateOf("") }
    var result by remember { mutableStateOf(0.0) }

    val calculator = remember { LightingCalculator() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            "Базовий рівень: один параметр",
            style = MaterialTheme.typography.titleMedium
        )

        TextField(
            value = nominalPower,
            onValueChange = { nominalPower = it },
            label = { Text("Номінальна потужність лампи (Вт)") },
            singleLine = true
        )

        Button(onClick = {
            // 1 година на повній яскравості
            result = calculator.currentPower(nominalPower.toDoubleOrZero(), 100.0)
        }) {
            Text("Розрахувати")
        }

        Text(
            "Потужність при повній яскравості: %.1f Вт".format(result),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}
