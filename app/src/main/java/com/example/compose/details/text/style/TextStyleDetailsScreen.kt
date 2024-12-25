package com.example.compose.details.text.style

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TextStyleDetailsScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Preview_13()
    }
}

@Composable
private fun Greeting(
    text: String,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .background(Color.Red)
            .padding(1.dp)
            .background(Color.White)
    ) {
        Text(
            text = text,
        )
    }
}

@Composable
private fun Greeting(
    text: String,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = TextUnit.Unspecified,
    lineHeight: TextUnit = TextUnit.Unspecified,
    style: TextStyle = LocalTextStyle.current,
) {
    Box(
        modifier = modifier
            .background(Color.Red)
            .padding(1.dp)
            .background(Color.White)
    ) {
        Text(
            text = text,
            fontSize = fontSize,
            lineHeight = lineHeight,
            style = style,
        )
    }
}

@Preview
@Composable
private fun Preview_1() {
    MaterialTheme {
        Greeting(
            text = "ABCDEFGHI\nJKLMNOPQR\nSTUVWXYZ",
        )
    }
}

@Preview
@Composable
private fun Preview_2() {
    MaterialTheme {
        Greeting(
            text = "ABCDEFGHI\nJKLMNOPQR\nSTUVWXYZ",
            fontSize = 16.sp,
        )
    }
}

@Preview
@Composable
private fun Preview_3() {
    MaterialTheme {
        Greeting(
            text = "ABCDEFGHI\nJKLMNOPQR\nSTUVWXYZ",
            fontSize = 8.sp,
        )
    }
}

@Preview
@Composable
private fun Preview_4() {
    MaterialTheme {
        Greeting(
            text = "ABCDEFGHI\nJKLMNOPQR\nSTUVWXYZ",
            fontSize = 64.sp,
        )
    }
}

@Preview
@Composable
private fun Preview_5() {
    MaterialTheme {
        Row {
            Greeting(
                text = "ABCDEFGHI\nJKLMNOPQR\nSTUVWXYZ",
                fontSize = 8.sp,
            )
            Spacer(modifier = Modifier.width(16.dp))
            Greeting(
                text = "ABCDEFGHI\nJKLMNOPQR\nSTUVWXYZ",
                fontSize = 16.sp,
            )
        }
    }
}

@Preview
@Composable
private fun Preview_6() {
    MaterialTheme {
        Row {
            Greeting(
                text = "ABCDEFGHI\nJKLMNOPQR\nSTUVWXYZ",
                fontSize = 8.sp,
                lineHeight = 12.sp,
            )
            Spacer(modifier = Modifier.width(16.dp))
            Greeting(
                text = "ABCDEFGHI\nJKLMNOPQR\nSTUVWXYZ",
                fontSize = 16.sp,
            )
        }
    }
}

@Preview
@Composable
private fun Preview_7() {
    val bodyLarge = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp,
    )

    MaterialTheme {
        Greeting(
            text = "ABCDEFGHI\nJKLMNOPQR\nSTUVWXYZ",
            style = bodyLarge,
        )
    }
}

@Preview
@Composable
private fun Preview_8() {
    val typography = Typography(
        bodyLarge = TextStyle(
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.5.sp,
        )
    )

    MaterialTheme(typography = typography) {
        Greeting(
            text = "ABCDEFGHI\nJKLMNOPQR\nSTUVWXYZ",
            style = MaterialTheme.typography.bodyLarge,
        )
    }
}

@Preview
@Composable
private fun Preview_9() {
    val typography = Typography(
        bodyLarge = TextStyle(
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            lineHeight = 48.sp, // changed!!!
            letterSpacing = 0.5.sp,
        )
    )

    MaterialTheme(typography = typography) {
        Greeting(
            text = "ABCDEFGHI\nJKLMNOPQR\nSTUVWXYZ",
            style = MaterialTheme.typography.bodyLarge,
        )
    }
}

@Preview
@Composable
private fun Preview_10() {
    val typography = Typography(
        bodyLarge = TextStyle(
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            lineHeight = 48.sp, // changed!!!
            letterSpacing = 0.5.sp,
        )
    )

    MaterialTheme(typography = typography) {
        Greeting(
            text = "ABCDEFGHI\nJKLMNOPQR\nSTUVWXYZ",
    //      style = MaterialTheme.typography.bodyLarge,
        )
    }
}

@Preview
@Composable
private fun Preview_11() {
    val typography = Typography(
        bodyLarge = TextStyle(
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            lineHeight = 48.sp, // changed!!!
            letterSpacing = 0.5.sp,
        )
    )

    MaterialTheme(typography = typography) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Greeting(
                text = "ABCDEFGHI\nJKLMNOPQR\nSTUVWXYZ",
                style = MaterialTheme.typography.bodyLarge,
            )
            Spacer(modifier = Modifier.width(16.dp))
            Greeting(
                text = "ABCDEFGHI\nJKLMNOPQR\nSTUVWXYZ",
        //      style = MaterialTheme.typography.bodyLarge,
            )
        }
    }
}

@Preview
@Composable
private fun Preview_12() {
    val typography = Typography(
        bodyLarge = TextStyle(
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            lineHeight = 48.sp, // changed!!!
            letterSpacing = 0.5.sp,
        )
    )

    MaterialTheme(typography = typography) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Greeting(
                text = "ABCDEFGHI\nJKLMNOPQR\nSTUVWXYZ",
                style = MaterialTheme.typography.bodyLarge,
            )
            Spacer(modifier = Modifier.width(16.dp))
            val textStyle = MaterialTheme.typography.bodyLarge
            CompositionLocalProvider(LocalTextStyle provides textStyle) {
                Greeting(
                    text = "ABCDEFGHI\nJKLMNOPQR\nSTUVWXYZ",
                )
            }
        }
    }
}

@Preview
@Composable
private fun Preview_13() {
    val DefaultPlatformTextStyle = PlatformTextStyle(
        includeFontPadding = false
    )

    val DefaultLineHeightStyle = LineHeightStyle(
        alignment = LineHeightStyle.Alignment.Center,
        trim = LineHeightStyle.Trim.None,
    )

    val typography = Typography(
        bodyLarge = TextStyle(
            platformStyle = DefaultPlatformTextStyle,
            lineHeightStyle = DefaultLineHeightStyle,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            lineHeight = 48.sp,
            letterSpacing = 0.5.sp,
        )
    )

    MaterialTheme(typography = typography) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Greeting(
                text = "ABCDEFGHI\nJKLMNOPQR\nSTUVWXYZ",
                style = MaterialTheme.typography.bodyLarge,
            )
            Spacer(modifier = Modifier.width(16.dp))
            Greeting(
                text = "ABCDEFGHI\nJKLMNOPQR\nSTUVWXYZ",
        //      style = MaterialTheme.typography.bodyLarge,
            )
        }
    }
}
