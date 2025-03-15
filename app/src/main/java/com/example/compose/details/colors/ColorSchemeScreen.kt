package com.example.compose.details.colors

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

private val PredefinedLightColorScheme1 = lightColorScheme(
    background = Color.White,
    onBackground = Color.Black,
    surface = Color.White,
    onSurface = Color.Red,
)

@Preview(showBackground = true)
@Composable
fun ColorSchemeScreen_1() {
    MaterialTheme(colorScheme = PredefinedLightColorScheme1) {
        Column {
            Surface(
                color = MaterialTheme.colorScheme.background,
            ) {
                Text(text = "Text on Background")
            }
            Surface(
                color = MaterialTheme.colorScheme.surface,
            ) {
                Text(text = "Text on Surface")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ColorSchemeScreen_2() {
    MaterialTheme(colorScheme = PredefinedLightColorScheme1) {
        Column {
            Surface(
                color = MaterialTheme.colorScheme.background,
                contentColor = MaterialTheme.colorScheme.onBackground,
            ) {
                Text(text = "Text on Background")
            }
            Surface(
                color = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.onSurface,
            ) {
                Text(text = "Text on Surface")
            }
        }
    }
}

private val PredefinedLightColorScheme2 = lightColorScheme(
    background = Color.White,
    onBackground = Color.Black,
    surface = Color(0xFFFFFFFE),
    onSurface = Color.Red,
)

@Preview(showBackground = true)
@Composable
fun ColorSchemeScreen_3() {
    MaterialTheme(colorScheme = PredefinedLightColorScheme2) {
        Column {
            Surface(
                color = MaterialTheme.colorScheme.background,
            ) {
                Text(text = "Text on Background")
            }
            Surface(
                color = MaterialTheme.colorScheme.surface,
            ) {
                Text(text = "Text on Surface")
            }
        }
    }
}

private val PredefinedLightColorScheme3 = lightColorScheme(
    surface = Color.White,
    onSurface = Color.Red,
)

@Preview(showBackground = true)
@Composable
fun ColorSchemeScreen_4() {
    MaterialTheme(colorScheme = PredefinedLightColorScheme1) {
        Column {
            Surface(
                color = MaterialTheme.colorScheme.background,
            ) {
                Text(text = "Text on Background")
            }
            MaterialTheme(colorScheme = PredefinedLightColorScheme3) {
                Surface {
                    Text(text = "Text on Surface")
                }
            }
        }
    }
}
