package ua.kpi.practical_example_4.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ua.kpi.practical_example_4.DisplayFor

/** Перемикач рівнів складності (три кнопки в один рядок). */
@Composable
fun DisplayModeSelector(
    selected: DisplayFor,
    onSelectedChange: (DisplayFor) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Вибір режиму відображення",
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            DisplayFor.values().forEach { mode ->
                Button(
                    onClick = { onSelectedChange(mode) },
                    modifier = Modifier.weight(1f),
                    enabled = mode != selected,
                    contentPadding = PaddingValues(horizontal = 4.dp)
                ) {
                    Text(
                        text = when (mode) {
                            DisplayFor.BASIC_LEVEL -> "Базовий"
                            DisplayFor.MIDDLE_LEVEL -> "Середній"
                            DisplayFor.ADVANCED_LEVEL -> "Просунутий"
                        },
                        maxLines = 1
                    )
                }
            }
        }
    }
}
