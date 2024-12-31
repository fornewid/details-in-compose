package com.example.compose.details.snackbar

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.exclude
import androidx.compose.foundation.layout.onConsumedWindowInsetsChanged
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.offset
import androidx.compose.ui.util.fastForEach
import androidx.compose.ui.util.fastMap
import androidx.compose.ui.util.fastMapNotNull
import androidx.compose.ui.util.fastMaxBy

@Composable
fun BottomSnackbarScaffold(
    modifier: Modifier = Modifier,
    topBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    snackbarHost: @Composable () -> Unit = {},
    floatingActionButton: @Composable () -> Unit = {},
    floatingActionButtonPosition: FabPosition = FabPosition.End,
    containerColor: Color = MaterialTheme.colorScheme.background,
    contentColor: Color = contentColorFor(containerColor),
    contentWindowInsets: WindowInsets = ScaffoldDefaults.contentWindowInsets,
    content: @Composable (PaddingValues) -> Unit
) {
    val safeInsets = remember(contentWindowInsets) { MutableWindowInsets(contentWindowInsets) }
    Surface(
        modifier =
        modifier.onConsumedWindowInsetsChanged { consumedWindowInsets ->
            // Exclude currently consumed window insets from user provided contentWindowInsets
            safeInsets.insets = contentWindowInsets.exclude(consumedWindowInsets)
        },
        color = containerColor,
        contentColor = contentColor
    ) {
        BottomSnackbarScaffoldLayout(
            fabPosition = floatingActionButtonPosition,
            topBar = topBar,
            bottomBar = bottomBar,
            content = content,
            snackbar = snackbarHost,
            contentWindowInsets = safeInsets,
            fab = floatingActionButton
        )
    }
}

@Composable
private fun BottomSnackbarScaffoldLayout(
    fabPosition: FabPosition,
    topBar: @Composable () -> Unit,
    content: @Composable (PaddingValues) -> Unit,
    snackbar: @Composable () -> Unit,
    fab: @Composable () -> Unit,
    contentWindowInsets: WindowInsets,
    bottomBar: @Composable () -> Unit
) {
    SubcomposeLayout { constraints ->
        val layoutWidth = constraints.maxWidth
        val layoutHeight = constraints.maxHeight

        val looseConstraints = constraints.copy(minWidth = 0, minHeight = 0)

        val topBarPlaceables =
            subcompose(ScaffoldLayoutContent.TopBar, topBar).fastMap {
                it.measure(looseConstraints)
            }

        val topBarHeight = topBarPlaceables.fastMaxBy { it.height }?.height ?: 0

        val snackbarPlaceables =
            subcompose(ScaffoldLayoutContent.Snackbar, snackbar).fastMap {
                // respect only bottom and horizontal for snackbar and fab
                val leftInset = contentWindowInsets.getLeft(this@SubcomposeLayout, layoutDirection)
                val rightInset =
                    contentWindowInsets.getRight(this@SubcomposeLayout, layoutDirection)
                // offset the snackbar constraints by the insets values
                it.measure(looseConstraints.offset(-leftInset - rightInset, 0))
            }

        val snackbarWidth = snackbarPlaceables.fastMaxBy { it.width }?.width ?: 0

        val fabPlaceables =
            subcompose(ScaffoldLayoutContent.Fab, fab).fastMapNotNull { measurable ->
                // respect only bottom and horizontal for snackbar and fab
                val leftInset = contentWindowInsets.getLeft(this@SubcomposeLayout, layoutDirection)
                val rightInset =
                    contentWindowInsets.getRight(this@SubcomposeLayout, layoutDirection)
                val bottomInset = contentWindowInsets.getBottom(this@SubcomposeLayout)
                measurable
                    .measure(looseConstraints.offset(-leftInset - rightInset, -bottomInset))
                    .takeIf { it.height != 0 && it.width != 0 }
            }

        val fabPlacement =
            if (fabPlaceables.isNotEmpty()) {
                val fabWidth = fabPlaceables.fastMaxBy { it.width }!!.width
                val fabHeight = fabPlaceables.fastMaxBy { it.height }!!.height
                // FAB distance from the left of the layout, taking into account LTR / RTL
                val fabLeftOffset =
                    when (fabPosition) {
                        FabPosition.Start -> {
                            if (layoutDirection == LayoutDirection.Ltr) {
                                FabSpacing.roundToPx()
                            } else {
                                layoutWidth - FabSpacing.roundToPx() - fabWidth
                            }
                        }
                        FabPosition.End,
                        FabPosition.EndOverlay -> {
                            if (layoutDirection == LayoutDirection.Ltr) {
                                layoutWidth - FabSpacing.roundToPx() - fabWidth
                            } else {
                                FabSpacing.roundToPx()
                            }
                        }
                        else -> (layoutWidth - fabWidth) / 2
                    }

                FabPlacement(left = fabLeftOffset, width = fabWidth, height = fabHeight)
            } else {
                null
            }

        val bottomBarPlaceables =
            subcompose(ScaffoldLayoutContent.BottomBar) { bottomBar() }
                .fastMap { it.measure(looseConstraints) }

        val bottomBarHeight = bottomBarPlaceables.fastMaxBy { it.height }?.height
        val fabOffsetFromBottom =
            fabPlacement?.let {
                if (bottomBarHeight == null || fabPosition == FabPosition.EndOverlay) {
                    it.height +
                            FabSpacing.roundToPx() +
                            contentWindowInsets.getBottom(this@SubcomposeLayout)
                } else {
                    // Total height is the bottom bar height + the FAB height + the padding
                    // between the FAB and bottom bar
                    bottomBarHeight + it.height + FabSpacing.roundToPx()
                }
            }

        val bodyContentPlaceables =
            subcompose(ScaffoldLayoutContent.MainContent) {
                val insets = contentWindowInsets.asPaddingValues(this@SubcomposeLayout)
                val innerPadding =
                    PaddingValues(
                        top =
                        if (topBarPlaceables.isEmpty()) {
                            insets.calculateTopPadding()
                        } else {
                            topBarHeight.toDp()
                        },
                        bottom =
                        if (bottomBarPlaceables.isEmpty() || bottomBarHeight == null) {
                            insets.calculateBottomPadding()
                        } else {
                            bottomBarHeight.toDp()
                        },
                        start =
                        insets.calculateStartPadding(
                            (this@SubcomposeLayout).layoutDirection
                        ),
                        end =
                        insets.calculateEndPadding((this@SubcomposeLayout).layoutDirection)
                    )
                content(innerPadding)
            }
                .fastMap { it.measure(looseConstraints) }

        layout(layoutWidth, layoutHeight) {
            // Placing to control drawing order to match default elevation of each placeable

            bodyContentPlaceables.fastForEach { it.place(0, 0) }
            topBarPlaceables.fastForEach { it.place(0, 0) }
            // The bottom bar is always at the bottom of the layout
            bottomBarPlaceables.fastForEach { it.place(0, layoutHeight - (bottomBarHeight ?: 0)) }
            // Explicitly not using placeRelative here as `leftOffset` already accounts for RTL
            fabPlacement?.let { placement ->
                fabPlaceables.fastForEach {
                    it.place(placement.left, layoutHeight - fabOffsetFromBottom!!)
                }
            }
            snackbarPlaceables.fastForEach {
                it.place(
                    (layoutWidth - snackbarWidth) / 2 +
                            contentWindowInsets.getLeft(this@SubcomposeLayout, layoutDirection),
                    0
                )
            }
        }
    }
}
