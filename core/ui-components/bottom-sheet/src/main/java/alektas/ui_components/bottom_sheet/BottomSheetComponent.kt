package alektas.ui_components.bottom_sheet

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import alektas.ui_components.bottom_sheet.utils.PreUpPostDownNestedScrollConnection
import kotlinx.coroutines.CancellationException
import kotlin.math.roundToInt

/**
 * Possible values of [BottomSheetState].
 */
enum class BottomSheetValue {

    /**
     * The bottom sheet is invisible.
     */
    Hidden,

    /**
     * The bottom sheet is visible, but only showing its peek height.
     */
    Collapsed,

    /**
     * The bottom sheet is visible at its middle height.
     */
    SemiExpanded,

    /**
     * The bottom sheet is visible at its maximum height.
     */
    Expanded
}

/**
 * State of the persistent bottom sheet in [BottomSheetScaffold].
 *
 * @param initialValue The initial value of the state.
 * @param animationSpec The default animation that will be used to animate to a new state.
 * @param confirmStateChange Optional callback invoked to confirm or veto a pending state change.
 */
@ExperimentalMaterialApi
@Stable
class BottomSheetState(
    initialValue: BottomSheetValue,
    animationSpec: AnimationSpec<Float> = SwipeableDefaults.AnimationSpec,
    confirmStateChange: (BottomSheetValue) -> Boolean = { true }
) : SwipeableState<BottomSheetValue>(
    initialValue = initialValue,
    animationSpec = animationSpec,
    confirmStateChange = confirmStateChange
) {

    /**
     * Expand the bottom sheet with animation and suspend until it if fully expanded or animation
     * has been cancelled. This method will throw [CancellationException] if the animation is
     * interrupted
     *
     * @return the reason the expand animation ended
     */
    suspend fun expand() = animateTo(BottomSheetValue.Expanded)

    /**
     * Expand the bottom sheet by middle position with animation and suspend until it if fully expanded or animation
     * has been cancelled. This method will throw [CancellationException] if the animation is
     * interrupted
     *
     * @return the reason the expand animation ended
     */
    suspend fun semiExpand() = animateTo(BottomSheetValue.SemiExpanded)

    /**
     * Collapse the bottom sheet with animation and suspend until it if fully collapsed or animation
     * has been cancelled. This method will throw [CancellationException] if the animation is
     * interrupted
     *
     * @return the reason the collapse animation ended
     */
    suspend fun collapse() = animateTo(BottomSheetValue.Collapsed)

    /**
     * Hide the bottom sheet with animation and suspend until it if fully hidden or animation
     * has been cancelled. This method will throw [CancellationException] if the animation is
     * interrupted
     *
     * @return the reason the collapse animation ended
     */
    suspend fun hide() = animateTo(BottomSheetValue.Hidden)

    companion object {
        /**
         * The default [Saver] implementation for [BottomSheetState].
         */
        fun Saver(
            animationSpec: AnimationSpec<Float>,
            confirmStateChange: (BottomSheetValue) -> Boolean
        ): Saver<BottomSheetState, *> = Saver(
            save = { it.currentValue },
            restore = {
                BottomSheetState(
                    initialValue = it,
                    animationSpec = animationSpec,
                    confirmStateChange = confirmStateChange
                )
            }
        )
    }

    internal val nestedScrollConnection = PreUpPostDownNestedScrollConnection
}

/**
 * Create a [BottomSheetState] and remember it.
 *
 * @param initialValue The initial value of the state.
 * @param animationSpec The default animation that will be used to animate to a new state.
 * @param confirmStateChange Optional callback invoked to confirm or veto a pending state change.
 */
@Composable
@ExperimentalMaterialApi
fun rememberBottomSheetState(
    initialValue: BottomSheetValue,
    animationSpec: AnimationSpec<Float> = SwipeableDefaults.AnimationSpec,
    confirmStateChange: (BottomSheetValue) -> Boolean = { true }
): BottomSheetState {
    return rememberSaveable(
        animationSpec,
        saver = BottomSheetState.Saver(
            animationSpec = animationSpec,
            confirmStateChange = confirmStateChange
        )
    ) {
        BottomSheetState(
            initialValue = initialValue,
            animationSpec = animationSpec,
            confirmStateChange = confirmStateChange
        )
    }
}

/**
 * <a href="https://material.io/components/sheets-bottom#standard-bottom-sheet" class="external" target="_blank">Material Design standard bottom sheet</a>.
 *
 * Standard bottom sheets co-exist with the screen’s main UI region and allow for simultaneously
 * viewing and interacting with both regions. They are commonly used to keep a feature or
 * secondary content visible on screen when content in main UI region is frequently scrolled or
 * panned.
 *
 * ![Standard bottom sheet image](https://developer.android.com/images/reference/androidx/compose/material/standard-bottom-sheet.png)
 *
 * This component provides an API to put together several material components to construct your
 * screen. For a similar component which implements the basic material design layout strategy
 * with app bars, floating action buttons and navigation drawers, use the standard [Scaffold].
 * For similar component that uses a backdrop as the centerpiece of the screen, use
 * [BackdropScaffold].
 *
 * @param sheetContent The content of the bottom sheet.
 * @param modifier An optional [Modifier] for the root of the scaffold.
 * @param bottomSheetState The state of the bottom sheet.
 * @param sheetGesturesEnabled Whether the bottom sheet can be interacted with by gestures.
 * @param sheetShape The shape of the bottom sheet.
 * @param sheetElevation The elevation of the bottom sheet.
 * @param sheetBackgroundColor The background color of the bottom sheet.
 * @param sheetContentColor The preferred content color provided by the bottom sheet to its
 * children. Defaults to the matching content color for [sheetBackgroundColor], or if that is
 * not a color from the theme, this will keep the same content color set above the bottom sheet.
 * @param sheetPeekHeight The height of the bottom sheet when it is collapsed.
 * @param content The main content of the screen. You should use the provided [PaddingValues]
 * to properly offset the content, so that it is not obstructed by the bottom sheet when collapsed.
 */
@Composable
@OptIn(ExperimentalMaterialApi::class)
fun BottomSheetScaffold(
    sheetContent: @Composable ColumnScope.() -> Unit,
    modifier: Modifier = Modifier,
    bottomSheetState: BottomSheetState = rememberBottomSheetState(BottomSheetValue.Collapsed),
    sheetHiddenStateEnabled: Boolean = false,
    sheetExpandedStateEnabled: Boolean = false,
    sheetGesturesEnabled: Boolean = true,
    sheetShape: Shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
    sheetElevation: Dp = BottomSheetDefaults.Elevation,
    sheetBackgroundColor: Color = MaterialTheme.colorScheme.surface,
    sheetContentColor: Color = contentColorFor(sheetBackgroundColor),
    sheetPeekHeight: Dp = BottomSheetDefaults.PeekHeight,
    sheetSemiExpandedHeight: Dp = BottomSheetDefaults.SemiExpandedHeight,
    backgroundColor: Color = MaterialTheme.colorScheme.background,
    contentColor: Color = contentColorFor(backgroundColor),
    content: @Composable (PaddingValues) -> Unit
) {
    BoxWithConstraints(modifier) {
        val fullHeight = constraints.maxHeight.toFloat()
        val peekHeightPx = with(LocalDensity.current) { sheetPeekHeight.toPx() }
        val semiExpandedHeightPx = with(LocalDensity.current) { sheetSemiExpandedHeight.toPx() }

        val swipeable = Modifier
            .nestedScroll(bottomSheetState.nestedScrollConnection)
            .swipeable(
                state = bottomSheetState,
                anchors = buildMap {
                    if (sheetHiddenStateEnabled) put(fullHeight, BottomSheetValue.Hidden)
                    put(fullHeight - peekHeightPx, BottomSheetValue.Collapsed)
                    put(fullHeight - semiExpandedHeightPx, BottomSheetValue.SemiExpanded)
                    if (sheetExpandedStateEnabled) put(0f, BottomSheetValue.Expanded)
                },
                orientation = Orientation.Vertical,
                enabled = sheetGesturesEnabled,
                resistance = null
            )

        val minHeight = if (sheetHiddenStateEnabled) 0.dp else sheetPeekHeight
        BottomSheetScaffoldStack(
            body = {
                Surface(
                    color = backgroundColor,
                    contentColor = contentColor
                ) {
                    Column(Modifier.fillMaxSize()) {
                        content(PaddingValues(bottom = minHeight))
                    }
                }
            },
            bottomSheet = {
                Surface(
                    swipeable
                        .fillMaxWidth()
                        .requiredHeightIn(min = minHeight),
                    shape = sheetShape,
                    elevation = sheetElevation,
                    color = sheetBackgroundColor,
                    contentColor = sheetContentColor,
                    content = {
                        Column(
                            modifier = Modifier.height(fullHeight.dp),
                            content = sheetContent
                        )
                    }
                )
            },
            bottomSheetOffset = bottomSheetState.offset,
        )
    }
}

@Composable
private fun BottomSheetScaffoldStack(
    body: @Composable () -> Unit,
    bottomSheet: @Composable () -> Unit,
    bottomSheetOffset: State<Float>,
) {
    Layout(
        content = {
            body()
            bottomSheet()
        }
    ) { measurables, constraints ->
        val placeable = measurables.first().measure(constraints)

        layout(placeable.width, placeable.height) {
            placeable.placeRelative(0, 0)

            val sheetPlaceable = measurables[1].measure(constraints.copy(minWidth = 0, minHeight = 0))
            val sheetOffsetY = bottomSheetOffset.value.roundToInt()
            sheetPlaceable.placeRelative(0, sheetOffsetY)
        }
    }
}

/**
 * Contains useful defaults for [BottomSheetScaffold].
 */
object BottomSheetDefaults {

    /**
     * The default elevation used by [BottomSheetScaffold].
     */
    val Elevation = 8.dp

    /**
     * Low height used by [BottomSheetScaffold].
     */
    val PeekHeight = 88.dp

    /**
     * Middle height used by [BottomSheetScaffold].
     */
    val SemiExpandedHeight = 264.dp
}
