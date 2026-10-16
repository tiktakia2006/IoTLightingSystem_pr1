package ua.kpi.practical_example_2.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
 * Просунутий рівень: уся логіка в класі LightingCalculator (data/).
 * UI лише збирає введені дані у State та показує результат.
 */
@Composable
fun AdvancedEnergyApp() {
    // --- State: введені користувачем значення ---
    var nominalPower by remember { mutableStateOf("") }
    var brightness by remember { mutableStateOf("") }
    var hoursPerDay by remember { mutableStateOf("") }
    var occupancy by remember { mutableStateOf("") }
    var tariff by remember { mutableStateOf("") }

    // --- State: результати ---
    var power by remember { mutableStateOf(0.0) }
    var energy by remember { mutableStateOf(0.0) }
    var savings by remember { mutableStateOf(0.0) }
    var cost by remember { mutableStateOf(0.0) }

    val calculator = remember { LightingCalculator() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()) // щоб усе влізло на малих екранах
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(
            "Просунутий рівень: масштабування проєкту",
            style = MaterialTheme.typography.titleMedium
        )

        TextField(nominalPower, { nominalPower = it },
            label = { Text("Номінальна потужність (Вт)") }, singleLine = true)
        TextField(brightness, { brightness = it },
            label = { Text("Яскравість (%)") }, singleLine = true)
        TextField(hoursPerDay, { hoursPerDay = it },
            label = { Text("Години роботи на добу") }, singleLine = true)
        TextField(occupancy, { occupancy = it },
            label = { Text("Присутність людей (% часу)") }, singleLine = true)
        TextField(tariff, { tariff = it },
            label = { Text("Тариф (грн/кВт·год)") }, singleLine = true)

        Button(onClick = {
            // Уся математика — у LightingCalculator, а не в UI
            power = calculator.currentPower(
                nominalPower.toDoubleOrZero(),
                brightness.toDoubleOrZero()
            )
            energy = calculator.dailyEnergyKwh(power, hoursPerDay.toDoubleOrZero())
            savings = calculator.savingsWithMotionSensor(energy, occupancy.toDoubleOrZero())
            cost = calculator.cost(energy - savings, tariff.toDoubleOrZero())
        }) {
            Text("Розрахувати")
        }

        val resultStyle = MaterialTheme.typography.bodyLarge
        Text("Потужність: %.1f Вт".format(power), style = resultStyle)
        Text("Споживання за добу: %.3f кВт·год".format(energy), style = resultStyle)
        Text("Економія від датчика руху: %.3f кВт·год".format(savings), style = resultStyle)
        Text("Вартість за добу: %.2f грн".format(cost), style = resultStyle)
    }
}
