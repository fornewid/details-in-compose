package com.example.compose.details.snackbar

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.exclude
import androidx.compose.foundation.layout.onConsumedWindowInsetsChanged
import androidx.compose.foundation.layout.systemBars
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
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.offset
import androidx.compose.ui.util.fastForEach
import androidx.compose.ui.util.fastMap
import androidx.compose.ui.util.fastMapNotNull
import androidx.compose.ui.util.fastMaxBy

@Composable
fun CustomScaffold(
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
        ScaffoldLayout(
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
private fun ScaffoldLayout(
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
                val bottomInset = contentWindowInsets.getBottom(this@SubcomposeLayout)
                // offset the snackbar constraints by the insets values
                it.measure(looseConstraints.offset(-leftInset - rightInset, -bottomInset))
            }

        val snackbarHeight = snackbarPlaceables.fastMaxBy { it.height }?.height ?: 0
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

        val snackbarOffsetFromBottom =
            if (snackbarHeight != 0) {
                snackbarHeight +
                        contentWindowInsets.getBottom(this@SubcomposeLayout)
            } else {
                0
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
                    layoutHeight - snackbarOffsetFromBottom
                )
            }
        }
    }
}

/** Object containing various default values for [Scaffold] component. */
object ScaffoldDefaults {
    /** Default insets to be used and consumed by the scaffold content slot */
    val contentWindowInsets: WindowInsets
        @Composable get() = WindowInsets.systemBars
}

/** The possible positions for a [FloatingActionButton] attached to a [Scaffold]. */
@kotlin.jvm.JvmInline
value class FabPosition internal constructor(@Suppress("unused") private val value: Int) {
    companion object {
        /**
         * Position FAB at the bottom of the screen at the start, above the [NavigationBar] (if it
         * exists)
         */
        val Start = FabPosition(0)

        /**
         * Position FAB at the bottom of the screen in the center, above the [NavigationBar] (if it
         * exists)
         */
        val Center = FabPosition(1)

        /**
         * Position FAB at the bottom of the screen at the end, above the [NavigationBar] (if it
         * exists)
         */
        val End = FabPosition(2)

        /**
         * Position FAB at the bottom of the screen at the end, overlaying the [NavigationBar] (if
         * it exists)
         */
        val EndOverlay = FabPosition(3)
    }

    override fun toString(): String {
        return when (this) {
            Start -> "FabPosition.Start"
            Center -> "FabPosition.Center"
            End -> "FabPosition.End"
            else -> "FabPosition.EndOverlay"
        }
    }
}

/**
 * Placement information for a [FloatingActionButton] inside a [Scaffold].
 *
 * @property left the FAB's offset from the left edge of the bottom bar, already adjusted for RTL
 *   support
 * @property width the width of the FAB
 * @property height the height of the FAB
 */
@Immutable internal class FabPlacement(val left: Int, val width: Int, val height: Int)

// FAB spacing above the bottom bar / bottom of the Scaffold
internal val FabSpacing = 16.dp

internal enum class ScaffoldLayoutContent {
    TopBar,
    MainContent,
    Snackbar,
    Fab,
    BottomBar
}

/**
 * A [WindowInsets] whose values can change without changing the instance. This is useful to avoid
 * recomposition when [WindowInsets] can change.
 *
 * Copied from [androidx.compose.foundation.layout.MutableWindowInsets], which is marked as
 * experimental and thus cannot be used cross-module.
 */
internal class MutableWindowInsets(
    initialInsets: WindowInsets = WindowInsets(0, 0, 0, 0),
) : WindowInsets {
    /**
     * The [WindowInsets] that are used for [left][getLeft], [top][getTop], [right][getRight], and
     * [bottom][getBottom] values.
     */
    var insets by mutableStateOf(initialInsets)

    override fun getLeft(density: Density, layoutDirection: LayoutDirection): Int =
        insets.getLeft(density, layoutDirection)

    override fun getTop(density: Density): Int = insets.getTop(density)

    override fun getRight(density: Density, layoutDirection: LayoutDirection): Int =
        insets.getRight(density, layoutDirection)

    override fun getBottom(density: Density): Int = insets.getBottom(density)
}
