package com.example.compose.details.text.dp

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomTheme(
    useDpAsTextUnit: Boolean,
    content: @Composable () -> Unit,
) {
    val density = LocalDensity.current
    val typography: CustomTypography = remember(density) {
        CustomTypography(CustomTypographyTokens(density))
    }
    MaterialTheme(
        typography = if (useDpAsTextUnit) {
            typography.dp
        } else {
            typography.sp
        },
        content = content,
    )
}

private val SpTypography = Typography(
    titleLarge = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.0.sp,
        letterSpacing = 0.0.sp,
    )
)

@Immutable
private class CustomTypography(
    private val tokens: CustomTypographyTokens,
    val dp: Typography = Typography(
        titleLarge = tokens.titleLarge
    ),
    val sp: Typography = SpTypography,
)

@Immutable
internal class CustomTypographyTokens(private val density: Density) {

    @Stable
    private val Int.sp: TextUnit
        get() = with(density) { this@sp.dp.toSp() }

    val titleLarge = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.0.sp,
        letterSpacing = 0.0.sp,
    )
}
