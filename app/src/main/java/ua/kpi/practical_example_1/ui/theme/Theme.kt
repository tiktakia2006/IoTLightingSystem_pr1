package ua.kpi.practical_example_1.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Amber80,
    secondary = AmberGrey80,
    tertiary = Blue80
)

private val LightColorScheme = lightColorScheme(
    primary = Amber40,
    secondary = AmberGrey40,
    tertiary = Blue40
)

/**
 * Тема ПЗ №1. dynamicColor = false, щоб вигляд був однаковим
 * на всіх пристроях (на Android 12+ інакше кольори брались би з шпалер).
 */
@Composable
fun Practical_Example_1Theme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
