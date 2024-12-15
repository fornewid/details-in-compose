package com.example.compose.details.text.dp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.PreviewFontScale
import androidx.compose.ui.unit.dp

@PreviewFontScale
@Composable
private fun DpAsTextUnitPreview() {
    CustomTheme(useDpAsTextUnit = true) {
        Box(modifier = Modifier
            .background(Color.Red)
            .padding(8.dp)
            .background(Color.White)) {
            Text(
                text = "Hello Android!",
                style = MaterialTheme.typography.titleLarge,
            )
        }
    }
}
