package com.example.compose.details.snackbar

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.SnackbarDefaults
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.semantics.isContainer
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

private const val ShortMessage = "Hello, Snackbar!"

@Preview(widthDp = 320, heightDp = 480)
@Composable
fun DefaultSnackbarScreen() {
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    val onClick: () -> Unit = {
        coroutineScope.launch {
            snackbarHostState.showSnackbar(ShortMessage)
        }
    }
    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onClick)
        },
    ) { paddingValues ->
        Content(
            onClick = onClick,
            contentPadding = paddingValues,
        )
    }
}

@Preview(widthDp = 320, heightDp = 480)
@Composable
fun CustomSnackbarScreen1_A() {
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    val onClick: () -> Unit = {
        coroutineScope.launch {
            snackbarHostState.showSnackbar(ShortMessage)
        }
    }
    Scaffold(
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState,
                snackbar = {
                    Snackbar(
                        snackbarData = it,
                        shape = CircleShape,
                        containerColor = MaterialTheme.colorScheme.error,
                        contentColor = MaterialTheme.colorScheme.onError,
                    )
                },
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onClick)
        },
    ) { paddingValues ->
        Content(
            onClick = onClick,
            contentPadding = paddingValues,
        )
    }
}

@Preview(widthDp = 320, heightDp = 480)
@Composable
fun CustomSnackbarScreen1_B() {
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    val onClick: () -> Unit = {
        coroutineScope.launch {
            snackbarHostState.showSnackbar(ShortMessage)
        }
    }
    Scaffold(
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState,
                snackbar = { CustomSnackbar_A(it) },
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onClick)
        },
    ) { paddingValues ->
        Content(
            onClick = onClick,
            contentPadding = paddingValues,
        )
    }
}

@Preview(widthDp = 320, heightDp = 480)
@Composable
fun CustomSnackbarScreen1_C() {
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    val onClick: () -> Unit = {
        coroutineScope.launch {
            snackbarHostState.showSnackbar(ShortMessage)
        }
    }
    Scaffold(
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState,
                snackbar = {
                    Snackbar(modifier = Modifier.padding(12.dp)) {
                        Text(
                            text = it.visuals.message,
                            fontStyle = FontStyle.Italic,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.Monospace,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                        )
                    }
                },
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onClick)
        },
    ) { paddingValues ->
        Content(
            onClick = onClick,
            contentPadding = paddingValues,
        )
    }
}

@Preview(widthDp = 320, heightDp = 480)
@Composable
fun CustomSnackbarScreen2() {
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    val onClick: () -> Unit = {
        coroutineScope.launch {
            snackbarHostState.currentSnackbarData?.dismiss()
            snackbarHostState.showSnackbar(ShortMessage)
        }
    }
    Scaffold(
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState,
                snackbar = { CustomSnackbar_A(it) },
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onClick)
        },
    ) { paddingValues ->
        Content(
            onClick = onClick,
            contentPadding = paddingValues,
        )
    }
}

@Preview(widthDp = 320, heightDp = 480)
@Composable
fun CustomSnackbarScreen3_A() {
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    val onClick: () -> Unit = {
        coroutineScope.launch {
            snackbarHostState.currentSnackbarData?.dismiss()
            snackbarHostState.showSnackbar(ShortMessage)
        }
    }
    Scaffold(
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState,
            )
        },
    ) { paddingValues ->
        Content(
            onClick = onClick,
            showOutlineButton = true,
            contentPadding = paddingValues,
        )
    }
}

@Preview(widthDp = 320, heightDp = 480)
@Composable
fun CustomSnackbarScreen3_B() {
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    val onClick: () -> Unit = {
        coroutineScope.launch {
            snackbarHostState.currentSnackbarData?.dismiss()
            snackbarHostState.showSnackbar(ShortMessage)
        }
    }
    Scaffold(
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState,
                snackbar = { CustomSnackbar_B(it) },
            )
        },
    ) { paddingValues ->
        Content(
            onClick = onClick,
            showOutlineButton = true,
            contentPadding = paddingValues,
        )
    }
}

@Preview(widthDp = 320, heightDp = 480)
@Composable
fun CustomSnackbarScreen4_A() {
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    val onClick: () -> Unit = {
        coroutineScope.launch {
            snackbarHostState.currentSnackbarData?.dismiss()
            snackbarHostState.showSnackbar(ShortMessage + " " + Random.nextInt(100))
        }
    }
    Scaffold(
        snackbarHost = {
            CustomSnackbarHost_A(
                hostState = snackbarHostState,
                snackbar = { CustomSnackbar_B(it) },
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onClick)
        },
    ) { paddingValues ->
        Content(
            onClick = onClick,
            contentPadding = paddingValues,
        )
    }
}

@Preview(widthDp = 320, heightDp = 480)
@Composable
fun CustomSnackbarScreen4_B() {
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    val onClick: () -> Unit = {
        coroutineScope.launch {
            snackbarHostState.currentSnackbarData?.dismiss()
            snackbarHostState.showSnackbar(ShortMessage + " " + Random.nextInt(100))
        }
    }
    Scaffold(
        snackbarHost = {
            CustomSnackbarHost_B(
                hostState = snackbarHostState,
                snackbar = { CustomSnackbar_B(it) },
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onClick)
        },
    ) { paddingValues ->
        Content(
            onClick = onClick,
            contentPadding = paddingValues,
        )
    }
}

@Preview(widthDp = 320, heightDp = 480)
@Composable
fun CustomSnackbarScreen5_A() {
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    val onClick: () -> Unit = {
        coroutineScope.launch {
            snackbarHostState.currentSnackbarData?.dismiss()
            snackbarHostState.showSnackbar(ShortMessage)
        }
    }
    Scaffold(
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState,
                snackbar = { CustomSnackbar_B(it) },
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onClick)
        },
        bottomBar = {
            BottomNavigationBar()
        },
    ) { paddingValues ->
        Content(
            onClick = onClick,
            contentPadding = paddingValues,
        )
    }
}

@Preview(widthDp = 320, heightDp = 480)
@Composable
fun CustomSnackbarScreen5_B() {
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    val onClick: () -> Unit = {
        coroutineScope.launch {
            snackbarHostState.currentSnackbarData?.dismiss()
            snackbarHostState.showSnackbar(ShortMessage)
        }
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = onClick)
        },
        bottomBar = {
            BottomNavigationBar()
        },
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            Content(onClick = onClick)

            SnackbarHost(
                hostState = snackbarHostState,
                modifier = Modifier.align(Alignment.BottomCenter),
                snackbar = { CustomSnackbar_B(it) },
            )
        }
    }
}

@Preview(widthDp = 320, heightDp = 480)
@Composable
fun CustomSnackbarScreen5_C() {
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    val onClick: () -> Unit = {
        coroutineScope.launch {
            snackbarHostState.currentSnackbarData?.dismiss()
            snackbarHostState.showSnackbar(ShortMessage)
        }
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = onClick)
        },
        bottomBar = {
            BottomNavigationBar()
        },
    ) { paddingValues ->
        Box {
            Content(
                onClick = onClick,
                contentPadding = paddingValues,
            )

            SnackbarHost(
                hostState = snackbarHostState,
                modifier = Modifier.align(Alignment.BottomCenter),
                snackbar = { CustomSnackbar_B(it) },
            )
        }
    }
}

@Preview(widthDp = 320, heightDp = 480)
@Preview(name = "with SystemUI", showSystemUi = true, device = Devices.PIXEL)
@Composable
fun CustomSnackbarScreen5_D() {
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    val onClick: () -> Unit = {
        coroutineScope.launch {
            snackbarHostState.currentSnackbarData?.dismiss()
            snackbarHostState.showSnackbar(ShortMessage)
        }
    }

    Box {
        Scaffold(
            floatingActionButton = {
                FloatingActionButton(onClick = onClick)
            },
            bottomBar = {
                BottomNavigationBar()
            },
        ) { paddingValues ->
            Content(
                onClick = onClick,
                contentPadding = paddingValues,
            )
        }

        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier.align(Alignment.BottomCenter),
            snackbar = { CustomSnackbar_B(it) },
        )
    }
}

@Preview(widthDp = 320, heightDp = 480)
@Preview(name = "with SystemUI", showSystemUi = true, device = Devices.PIXEL)
@Composable
fun CustomSnackbarScreen5_E() {
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    val onClick: () -> Unit = {
        coroutineScope.launch {
            snackbarHostState.currentSnackbarData?.dismiss()
            snackbarHostState.showSnackbar(ShortMessage)
        }
    }

    CustomScaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = onClick)
        },
        bottomBar = {
            BottomNavigationBar()
        },
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState,
                snackbar = { CustomSnackbar_B(it) },
            )
        },
    ) { paddingValues ->
        Content(
            onClick = onClick,
            contentPadding = paddingValues,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(widthDp = 320, heightDp = 480)
@Preview(name = "with SystemUI", showSystemUi = true, device = Devices.PIXEL)
@Composable
fun CustomSnackbarScreen6() {
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    val onClick: () -> Unit = {
        coroutineScope.launch {
            snackbarHostState.currentSnackbarData?.dismiss()
            snackbarHostState.showSnackbar(ShortMessage)
        }
    }
    BottomSnackbarScaffold(
        topBar = {
            TopAppBar(title = { Text("Custom Snackbar") })
        },
        snackbarHost = {
            CustomSnackbarHost_C(
                hostState = snackbarHostState,
                snackbar = {
                    CustomSnackbar_C(
                        snackbarData = it,
                        shape = RectangleShape,
                    )
                },
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onClick)
        },
        bottomBar = {
            BottomNavigationBar()
        },
    ) { paddingValues ->
        Content(
            onClick = onClick,
            contentPadding = paddingValues,
        )
    }
}

@Composable
private fun Content(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    showOutlineButton: Boolean = false,
    contentPadding: PaddingValues = PaddingValues(0.dp),
) {
    Box(
        modifier = modifier
            .padding(contentPadding)
            .fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Button(onClick = onClick) {
            Text(text = "Show Snackbar!")
        }
        if (showOutlineButton) {
            OutlinedButton(
                onClick = onClick,
                modifier = Modifier.align(Alignment.BottomCenter),
            ) {
                Text(text = "Show Snackbar!")
            }
        }
    }
}

@Composable
private fun FloatingActionButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    ExtendedFloatingActionButton(
        text = { Text("Show Snackbar!") },
        icon = { Icon(Icons.Filled.Refresh, contentDescription = null) },
        modifier = modifier,
        onClick = onClick,
    )
}

@Composable
private fun BottomNavigationBar(
    modifier: Modifier = Modifier,
) {
    val items = listOf("A", "B", "C", "D")
    NavigationBar(modifier = modifier) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = { Icon(Icons.Filled.Info, contentDescription = null) },
                label = { Text(item) },
                selected = index == 0,
                onClick = {},
            )
        }
    }
}

@Composable
private fun CustomSnackbarHost_A(
    hostState: SnackbarHostState,
    modifier: Modifier = Modifier,
    snackbar: @Composable (SnackbarData) -> Unit = { Snackbar(it) }
) {
    val currentSnackbarData = hostState.currentSnackbarData
    LaunchedEffect(currentSnackbarData) {
        if (currentSnackbarData != null) {
            val duration = when (currentSnackbarData.visuals.duration) {
                SnackbarDuration.Indefinite -> Long.MAX_VALUE
                SnackbarDuration.Long -> 10000L
                SnackbarDuration.Short -> 4000L
            }
            delay(duration)
            currentSnackbarData.dismiss()
        }
    }
    Crossfade(
        targetState = hostState.currentSnackbarData,
        modifier = modifier,
        content = { current ->
            if (current != null) snackbar(current)
        }
    )
}

@Composable
private fun CustomSnackbarHost_B(
    hostState: SnackbarHostState,
    modifier: Modifier = Modifier,
    snackbar: @Composable (SnackbarData) -> Unit = { Snackbar(it) }
) {
    val currentSnackbarData = hostState.currentSnackbarData
    LaunchedEffect(currentSnackbarData) {
        if (currentSnackbarData != null) {
            val duration = when (currentSnackbarData.visuals.duration) {
                SnackbarDuration.Indefinite -> Long.MAX_VALUE
                SnackbarDuration.Long -> 10000L
                SnackbarDuration.Short -> 4000L
            }
            delay(duration)
            currentSnackbarData.dismiss()
        }
    }
    FadeInFadeOut(
        current = hostState.currentSnackbarData,
        modifier = modifier,
        content = snackbar
    )
}

@Composable
private fun CustomSnackbarHost_C(
    hostState: SnackbarHostState,
    modifier: Modifier = Modifier,
    snackbar: @Composable (SnackbarData) -> Unit = { Snackbar(it) }
) {
    val currentSnackbarData = hostState.currentSnackbarData
    LaunchedEffect(currentSnackbarData) {
        if (currentSnackbarData != null) {
            val duration = when (currentSnackbarData.visuals.duration) {
                SnackbarDuration.Indefinite -> Long.MAX_VALUE
                SnackbarDuration.Long -> 10000L
                SnackbarDuration.Short -> 4000L
            }
            delay(duration)
            currentSnackbarData.dismiss()
        }
    }
    AnimatedContent(
        targetState = hostState.currentSnackbarData,
        modifier = modifier,
        transitionSpec = {
            slideInVertically(
                animationSpec = tween(
                    delayMillis = 150,
                    durationMillis = 150,
                )
            ) { -it } togetherWith slideOutVertically(
                animationSpec = tween(
                    durationMillis = 150,
                )
            ) { -it }
        },
        content = { current ->
            if (current != null) snackbar(current)
            else Box(modifier = Modifier.fillMaxWidth())
        }
    )
}

@Composable
private fun CustomSnackbar_A(
    snackbarData: SnackbarData,
    modifier: Modifier = Modifier,
    shape: Shape = SnackbarDefaults.shape,
    containerColor: Color = SnackbarDefaults.color,
    contentColor: Color = SnackbarDefaults.contentColor,
) {
    Surface(
        modifier = modifier.padding(12.dp),
        shape = shape,
        color = containerColor,
        contentColor = contentColor,
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = 48.dp)
                .padding(horizontal = 12.dp),
            contentAlignment = Alignment.CenterStart,
        ) {
            Text(
                text = snackbarData.visuals.message,
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Monospace,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

@Composable
private fun CustomSnackbar_B(
    snackbarData: SnackbarData,
    modifier: Modifier = Modifier,
    shape: Shape = SnackbarDefaults.shape,
    containerColor: Color = SnackbarDefaults.color,
    contentColor: Color = SnackbarDefaults.contentColor,
) {
    CompositionLocalProvider(LocalContentColor provides contentColor) {
        Box(
            modifier = modifier
                .padding(12.dp)
                .background(color = containerColor, shape = shape)
                .clip(shape)
                .semantics(mergeDescendants = false) {
                    @Suppress("DEPRECATION")
                    isContainer = true
                },
            propagateMinConstraints = true,
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .defaultMinSize(minHeight = 48.dp)
                    .padding(8.dp),
                contentAlignment = Alignment.CenterStart,
            ) {
                Text(
                    text = snackbarData.visuals.message,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }
}

@Composable
private fun CustomSnackbar_C(
    snackbarData: SnackbarData,
    modifier: Modifier = Modifier,
    shape: Shape = SnackbarDefaults.shape,
    containerColor: Color = SnackbarDefaults.color,
    contentColor: Color = SnackbarDefaults.contentColor,
    windowInsets: WindowInsets = WindowInsets.systemBars.only(
        WindowInsetsSides.Horizontal + WindowInsetsSides.Top
    ),
) {
    CompositionLocalProvider(LocalContentColor provides contentColor) {
        Box(
            modifier = modifier
                .background(color = containerColor, shape = shape)
                .clip(shape)
                .windowInsetsPadding(windowInsets)
                .semantics(mergeDescendants = false) {
                    @Suppress("DEPRECATION")
                    isContainer = true
                },
            propagateMinConstraints = true,
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .defaultMinSize(minHeight = 48.dp)
                    .padding(8.dp),
                contentAlignment = Alignment.CenterStart,
            ) {
                Text(
                    text = snackbarData.visuals.message,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }
}
