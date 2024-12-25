package com.example.compose.details.preview

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.content.res.Configuration.UI_MODE_TYPE_NORMAL
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp

@Composable
fun PreviewInternalsScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Preview_8()
    }
}

@Composable
private fun Greeting(
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
) {
    Text(
        text = "Hello Compose Preview!",
        color = color,
        modifier = modifier.padding(
            horizontal = 16.dp,
            vertical = 8.dp,
        ),
    )
}

@Preview
@Composable
private fun Preview_1() {
    MaterialTheme {
        Greeting()
    }
}

@Preview(name = "Light")
@Preview(name = "Dark", uiMode = UI_MODE_NIGHT_YES or UI_MODE_TYPE_NORMAL)
@Composable
private fun Preview_2() {
    MaterialTheme {
        Greeting()
    }
}

@Preview(name = "Light")
@Preview(name = "Dark", uiMode = UI_MODE_NIGHT_YES or UI_MODE_TYPE_NORMAL)
@Composable
private fun Preview_3() {
    val colorScheme = if (isSystemInDarkTheme()) {
        darkColorScheme()
    } else {
        lightColorScheme()
    }
    MaterialTheme(colorScheme = colorScheme) {
        Greeting()
    }
}

@Preview(name = "Light")
@Preview(name = "Dark", uiMode = UI_MODE_NIGHT_YES or UI_MODE_TYPE_NORMAL)
@Composable
private fun Preview_4() {
    val colorScheme = if (isSystemInDarkTheme()) {
        darkColorScheme()
    } else {
        lightColorScheme()
    }
    MaterialTheme(colorScheme = colorScheme) {
        Greeting(color = MaterialTheme.colorScheme.onSurface)
    }
}

@Preview(name = "Light")
@Preview(name = "Dark", uiMode = UI_MODE_NIGHT_YES or UI_MODE_TYPE_NORMAL, showBackground = true)
@Composable
private fun Preview_5() {
    val colorScheme = if (isSystemInDarkTheme()) {
        darkColorScheme()
    } else {
        lightColorScheme()
    }
    MaterialTheme(colorScheme = colorScheme) {
        Greeting(color = MaterialTheme.colorScheme.onSurface)
    }
}

@Preview(name = "Light")
@Preview(
    name = "Dark",
    uiMode = UI_MODE_NIGHT_YES or UI_MODE_TYPE_NORMAL,
    showBackground = true,
    backgroundColor = 0xFF000000,
)
@Composable
private fun Preview_6() {
    val colorScheme = if (isSystemInDarkTheme()) {
        darkColorScheme()
    } else {
        lightColorScheme()
    }
    MaterialTheme(colorScheme = colorScheme) {
        Greeting(color = MaterialTheme.colorScheme.onSurface)
    }
}

@PreviewLightDark
@Composable
private fun Preview_7() {
    val colorScheme = if (isSystemInDarkTheme()) {
        darkColorScheme()
    } else {
        lightColorScheme()
    }
    MaterialTheme(colorScheme = colorScheme) {
        Greeting(
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.background(
                color = MaterialTheme.colorScheme.surface,
            )
        )
    }
}

@PreviewLightDark
@Composable
private fun Preview_8() {
    val colorScheme = if (isSystemInDarkTheme()) {
        darkColorScheme()
    } else {
        lightColorScheme()
    }
    MaterialTheme(colorScheme = colorScheme) {
        Surface {
            Greeting()
        }
    }
}
