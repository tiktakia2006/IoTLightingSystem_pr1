package ua.kpi.practical_example_2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ua.kpi.practical_example_2.composables.AdvancedEnergyApp
import ua.kpi.practical_example_2.composables.BasicEnergyApp
import ua.kpi.practical_example_2.composables.DisplayModeSelector
import ua.kpi.practical_example_2.composables.MediumEnergyApp
import ua.kpi.practical_example_2.ui.theme.Practical_Example_2Theme

/** Точка входу ПЗ №2: розрахунок енергоспоживання системи освітлення. */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Practical_Example_2Theme {
                var displayFor by remember { mutableStateOf(DisplayFor.BASIC_LEVEL) }

                Surface(modifier = Modifier.fillMaxSize()) {
                    Column {
                        DisplayModeSelector(
                            selected = displayFor,
                            onSelectedChange = { displayFor = it }
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        when (displayFor) {
                            DisplayFor.BASIC_LEVEL -> BasicEnergyApp()
                            DisplayFor.MIDDLE_LEVEL -> MediumEnergyApp()
                            DisplayFor.ADVANCED_LEVEL -> AdvancedEnergyApp()
                        }
                    }
                }
            }
        }
    }
}
