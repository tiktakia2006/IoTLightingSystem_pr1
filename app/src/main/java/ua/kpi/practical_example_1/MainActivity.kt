package ua.kpi.practical_example_1

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
import ua.kpi.practical_example_1.composables.DisplayModeSelector
import ua.kpi.practical_example_1.composables.LightingAppLevel1
import ua.kpi.practical_example_1.composables.LightingAppLevel2
import ua.kpi.practical_example_1.composables.LightingAppLevel3
import ua.kpi.practical_example_1.ui.theme.Practical_Example_1Theme

/**
 * Точка входу ПЗ №1. Activity лише запускає Compose і показує
 * екран, обраний користувачем у DisplayModeSelector.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Practical_Example_1Theme {
                // Стан: який рівень складності зараз відкритий
                var displayFor by remember { mutableStateOf(DisplayFor.BASIC_LEVEL) }

                Surface(modifier = Modifier.fillMaxSize()) {
                    Column {
                        DisplayModeSelector(
                            selected = displayFor,
                            onSelectedChange = { displayFor = it }
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        // Показуємо екран, що відповідає вибраному рівню
                        when (displayFor) {
                            DisplayFor.BASIC_LEVEL -> LightingAppLevel1()
                            DisplayFor.MIDDLE_LEVEL -> LightingAppLevel2()
                            DisplayFor.ADVANCED_LEVEL -> LightingAppLevel3()
                        }
                    }
                }
            }
        }
    }
}
