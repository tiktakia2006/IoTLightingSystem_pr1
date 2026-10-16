package ua.kpi.practical_example_4

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
import ua.kpi.practical_example_4.composables.AdvancedLoginScreen
import ua.kpi.practical_example_4.composables.BasicLoginScreen
import ua.kpi.practical_example_4.composables.DisplayModeSelector
import ua.kpi.practical_example_4.composables.MediumLoginScreen
import ua.kpi.practical_example_4.ui.theme.Practical_Example_4Theme

/** Точка входу ПЗ №4: екрани авторизації та реєстрації. */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Practical_Example_4Theme {
                var displayFor by remember { mutableStateOf(DisplayFor.BASIC_LEVEL) }

                Surface(modifier = Modifier.fillMaxSize()) {
                    Column {
                        DisplayModeSelector(
                            selected = displayFor,
                            onSelectedChange = { displayFor = it }
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        when (displayFor) {
                            DisplayFor.BASIC_LEVEL -> BasicLoginScreen()
                            DisplayFor.MIDDLE_LEVEL -> MediumLoginScreen()
                            DisplayFor.ADVANCED_LEVEL -> AdvancedLoginScreen()
                        }
                    }
                }
            }
        }
    }
}
