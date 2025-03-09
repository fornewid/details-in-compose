package com.example.compose.details.target

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalMinimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalViewConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp

@Preview(widthDp = 100, heightDp = 100)
@Composable
fun TouchTargetScreen1_A() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            imageVector = Icons.Filled.Favorite,
            contentDescription = null,
        )
    }
}

@Preview(widthDp = 100, heightDp = 100)
@Composable
fun TouchTargetScreen1_B() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            imageVector = Icons.Filled.Favorite,
            contentDescription = null,
            modifier = Modifier.clickable(onClick = { /* do something */ })
        )
    }
}

@Preview(widthDp = 100, heightDp = 100)
@Composable
fun TouchTargetScreen1_C() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            imageVector = Icons.Filled.Favorite,
            contentDescription = null,
            modifier = Modifier
                .border(width = 1.dp, color = Color.Red)
                .clickable(onClick = { /* do something */ }),
        )
    }
}

@Preview(widthDp = 100, heightDp = 100)
@Composable
private fun TouchTargetScreen2_A() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            imageVector = Icons.Filled.Favorite,
            contentDescription = null,
            modifier = Modifier
                .border(width = 1.dp, color = Color.Red)
                .clickable(onClick = { /* do something */ })
                .size(48.dp),
        )
    }
}

@Preview(widthDp = 100, heightDp = 100)
@Composable
private fun TouchTargetScreen2_B() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            imageVector = Icons.Filled.Favorite,
            contentDescription = null,
            modifier = Modifier
                .border(width = 1.dp, color = Color.Red)
                .clickable(onClick = { /* do something */ })
                .size(48.dp)
                .padding(12.dp),
        )
    }
}

@Preview(widthDp = 100, heightDp = 100)
@Composable
private fun TouchTargetScreen3_A() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        IconWithClick(
            imageVector = Icons.Filled.Favorite,
            contentDescription = null,
            onClick = { /* do something */ },
            modifier = Modifier.border(width = 1.dp, color = Color.Red),
        )
    }
}

@Preview(widthDp = 100, heightDp = 100)
@Composable
private fun TouchTargetScreen3_B() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        IconWithClick(
            imageVector = Icons.Filled.Favorite,
            contentDescription = null,
            onClick = { /* do something */ },
            modifier = Modifier
                .border(width = 1.dp, color = Color.Red)
                .size(72.dp),
        )
    }
}

@Preview(widthDp = 100, heightDp = 100)
@Composable
private fun TouchTargetScreen3_C() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        IconWithClick(
            imageVector = Icons.Filled.Favorite,
            contentDescription = null,
            onClick = { /* do something */ },
            modifier = Modifier
                .border(width = 1.dp, color = Color.Red)
                .size(36.dp),
        )
    }
}

@Composable
private fun IconWithClick(
    onClick: () -> Unit,
    imageVector: ImageVector,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    tint: Color = LocalContentColor.current,
) {
    Icon(
        imageVector = imageVector,
        contentDescription = contentDescription,
        modifier = modifier
            .clickable(onClick = onClick)
            .size(48.dp)
            .padding(12.dp),
        tint = tint,
    )
}

@Preview(widthDp = 300, heightDp = 100)
@Composable
private fun TouchTargetScreen4() {
    Row(
        modifier = Modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        Box(Modifier.size(100.dp), Alignment.Center) {
            TouchTargetScreen4_A()
        }
        Box(Modifier.size(100.dp), Alignment.Center) {
            TouchTargetScreen4_B()
        }
        Box(Modifier.size(100.dp), Alignment.Center) {
            TouchTargetScreen4_C()
        }
    }
}

@Preview(widthDp = 100, heightDp = 100)
@Composable
private fun TouchTargetScreen4_A() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        IconWrapper(
            onClick = { /* do something */ },
            modifier = Modifier
                .border(width = 1.dp, color = Color.Blue),
        ) {
            Icon(
                imageVector = Icons.Filled.Favorite,
                contentDescription = null,
                modifier = Modifier.border(width = 1.dp, color = Color.Red),
            )
        }
    }
}

@Preview(widthDp = 100, heightDp = 100)
@Composable
private fun TouchTargetScreen4_B() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        IconWrapper(
            onClick = { /* do something */ },
            modifier = Modifier
                .border(width = 1.dp, color = Color.Blue)
                .size(72.dp),
        ) {
            Icon(
                imageVector = Icons.Filled.Favorite,
                contentDescription = null,
                modifier = Modifier.border(width = 1.dp, color = Color.Red),
            )
        }
    }
}

@Preview(widthDp = 100, heightDp = 100)
@Composable
private fun TouchTargetScreen4_C() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        CompositionLocalProvider(LocalMinimumInteractiveComponentSize provides 70.dp) {
            IconWrapper(
                onClick = { /* do something */ },
                modifier = Modifier
                    .border(width = 1.dp, color = Color.Blue)
                    .size(36.dp),
            ) {
                Icon(
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = null,
                    modifier = Modifier.border(width = 1.dp, color = Color.Red),
                )
            }
        }
    }
}

@Composable
private fun IconWrapper(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Box(
        modifier = modifier
            .size(48.dp)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center,
    ) {
        content()
    }
}

@Preview(widthDp = 300, heightDp = 100)
@Composable
private fun TouchTargetScreen5() {
    Row(
        modifier = Modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        Box(Modifier.size(100.dp), Alignment.Center) {
            TouchTargetScreen5_A()
        }
        Box(Modifier.size(100.dp), Alignment.Center) {
            TouchTargetScreen5_B()
        }
        Box(Modifier.size(100.dp), Alignment.Center) {
            TouchTargetScreen5_C()
        }
    }
}

@Preview(widthDp = 100, heightDp = 100)
@Composable
private fun TouchTargetScreen5_A() {
    Row(
        modifier = Modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        IconButton(
            onClick = { /* do something */ },
            modifier = Modifier.border(width = 1.dp, color = Color.Blue),
        ) {
            Icon(
                imageVector = Icons.Filled.Favorite,
                contentDescription = null,
                modifier = Modifier.border(width = 1.dp, color = Color.Red),
            )
        }
    }
}

@Preview(widthDp = 100, heightDp = 100)
@Composable
private fun TouchTargetScreen5_B() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        IconButton(
            onClick = { /* do something */ },
            modifier = Modifier
                .border(width = 1.dp, color = Color.Blue)
                .size(72.dp),
        ) {
            Icon(
                imageVector = Icons.Filled.Favorite,
                contentDescription = null,
                modifier = Modifier.border(width = 1.dp, color = Color.Red),
            )
        }
    }
}

@Preview(widthDp = 100, heightDp = 100)
@Composable
fun TouchTargetScreen5_C() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        IconButton(
            onClick = { /* do something */ },
            modifier = Modifier
                .border(width = 1.dp, color = Color.Blue)
                .size(36.dp),
        ) {
            Icon(
                imageVector = Icons.Filled.Favorite,
                contentDescription = null,
                modifier = Modifier.border(width = 1.dp, color = Color.Red),
            )
        }
    }
}

@Preview(widthDp = 200, heightDp = 100)
@Composable
fun TouchTargetScreen6() {
    Row(
        modifier = Modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        Box(Modifier.size(100.dp), Alignment.Center) {
            TouchTargetScreen5_A()
        }
        Box(Modifier.size(100.dp), Alignment.Center) {
            TouchTargetScreen6_A()
        }
    }
}

@Preview(widthDp = 100, heightDp = 100)
@Composable
fun TouchTargetScreen6_A() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        CompositionLocalProvider(LocalMinimumInteractiveComponentSize provides 36.dp) {
            IconButton(
                onClick = { /* do something */ },
                modifier = Modifier
                    .border(width = 1.dp, color = Color.Blue)
                    .size(36.dp),
            ) {
                Icon(
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = null,
                    modifier = Modifier.border(width = 1.dp, color = Color.Red),
                )
            }
        }
        Box(
            modifier = Modifier
                .requiredSize(48.dp)
                .border(width = 0.1.dp, color = Color.LightGray),
        )
    }
}

@Preview(widthDp = 100, heightDp = 100)
@Composable
fun TouchTargetScreen6_B() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        val minimumTouchTarget = 36.dp
        val viewConfiguration = CustomViewConfiguration(
            minimumTouchTargetSize = DpSize(minimumTouchTarget, minimumTouchTarget),
        )
        CompositionLocalProvider(
            LocalMinimumInteractiveComponentSize provides minimumTouchTarget,
            LocalViewConfiguration provides viewConfiguration,
        ) {
            IconButton(
                onClick = { /* do something */ },
                modifier = Modifier
                    .border(width = 1.dp, color = Color.Blue)
                    .size(36.dp),
            ) {
                Icon(
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = null,
                    modifier = Modifier.border(width = 1.dp, color = Color.Red),
                )
            }
        }
        Box(
            modifier = Modifier
                .requiredSize(48.dp)
                .border(width = 0.1.dp, color = Color.LightGray),
        )
    }
}

@Preview(widthDp = 200, heightDp = 100)
@Composable
fun TouchTargetScree7() {
    Row(
        modifier = Modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        Box(Modifier.size(100.dp), Alignment.Center) {
            TouchTargetScreen6_A()
        }
        Box(Modifier.size(100.dp), Alignment.Center) {
            TouchTargetScreen6_B()
        }
    }
}
