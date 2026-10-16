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
import ua.kpi.practical_example_2.data.toDoubleOrZero

/**
 * Середній рівень: два параметри (номінальна потужність і яскравість).
 * Обчислення винесене в окрему функцію calculatePowerMedium.
 */
@Composable
fun MediumEnergyApp() {
    var nominalPower by remember { mutableStateOf("") }
    var brightness by remember { mutableStateOf("") }
    var result by remember { mutableStateOf(0.0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            "Середній рівень: декілька параметрів",
            style = MaterialTheme.typography.titleMedium
        )

        TextField(
            value = nominalPower,
            onValueChange = { nominalPower = it },
            label = { Text("Номінальна потужність (Вт)") },
            singleLine = true
        )

        TextField(
            value = brightness,
            onValueChange = { brightness = it },
            label = { Text("Яскравість (%)") },
            singleLine = true
        )

        Button(onClick = { result = calculatePowerMedium(nominalPower, brightness) }) {
            Text("Розрахувати")
        }

        Text(
            "Поточна потужність: %.1f Вт".format(result),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

/** Логіка середнього рівня: P = P_ном × яскравість / 100. */
fun calculatePowerMedium(nominalPower: String, brightness: String): Double {
    val p = nominalPower.toDoubleOrZero()
    val b = brightness.toDoubleOrZero().coerceIn(0.0, 100.0)
    return p * b / 100.0
}
