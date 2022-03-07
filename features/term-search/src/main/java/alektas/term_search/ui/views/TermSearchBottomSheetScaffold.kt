package alektas.term_search.ui.views

import alektas.common.ui.TermCard
import alektas.common.ui.ShimmingTermCard
import alektas.common.ui.models.TermItem
import alektas.common.ui.utils.getPrimaryImage
import alektas.midic.theme.*
import alektas.term_search.ui.models.ScreenState
import alektas.ui_components.bottom_sheet.BottomSheetScaffold
import alektas.ui_components.bottom_sheet.BottomSheetState
import alektas.ui_components.bottom_sheet.BottomSheetValue
import alektas.ui_components.bottom_sheet.rememberBottomSheetState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TermSearchBottomSheetScaffold(
    modifier: Modifier = Modifier,
    bottomSheetContentState: ScreenState,
    bottomSheetState: BottomSheetState = rememberBottomSheetState(BottomSheetValue.Collapsed),
    onTermClick: (TermItem) -> Unit,
    content: @Composable (PaddingValues) -> Unit,
) {
    BottomSheetScaffold(
        modifier = modifier,
        content = content,
        sheetShape = RoundedCornerShape(topStart = cornersX6, topEnd = cornersX6),
        bottomSheetState = bottomSheetState,
        sheetBackgroundColor = MaterialTheme.colorScheme.surfaceVariant,
        sheetElevation = 0.dp,
        sheetPeekHeight = badgeHeight + paddingX2 * 2,
        sheetContent = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(modifier = Modifier.size(paddingX2))
                BottomSheetBadge()
                Spacer(modifier = Modifier.size(paddingX2))
                BottomSheetContent(bottomSheetContentState, onTermClick)
            }
        },
    )
}

@Composable
private fun BottomSheetContent(
    bottomSheetContentState: ScreenState,
    onTermClick: (TermItem) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = paddingX4)
    ) {
        when (bottomSheetContentState) {
            is ScreenState.Loading -> {
                items(6) {
                    ShimmingTermCard()
                    Spacer(modifier = Modifier.size(paddingX2))
                }
            }
            is ScreenState.NoResults -> {
                // show nothing
            }
            is ScreenState.Results -> {
                items(bottomSheetContentState.terms) { term ->
                    TermCard(
                        term = term,
                        modifier = Modifier.fillMaxWidth(),
                        imagePainter = term.getPrimaryImage(),
                        onItemClick = onTermClick,
                    )
                    Spacer(modifier = Modifier.size(paddingX2))
                }
            }
        }
    }
}

@Composable
private fun BottomSheetBadge() {
    Box(
        modifier = Modifier
            .height(badgeHeight)
            .width(56.dp)
            .clip(RoundedCornerShape(cornersX1))
            .background(MaterialTheme.colorScheme.tertiary)
    )
}

private val badgeHeight = 6.dp

@OptIn(ExperimentalMaterialApi::class)
@Preview
@Composable
fun TermSearchBottomSheetLightPreview() {
    AppTheme(useDarkTheme = false) {
        TermSearchBottomSheetScaffold(
            bottomSheetContentState = ScreenState.Results("Word", buildList {
                repeat(10) {
                    add(TermItem(0L, "Word $it", "/$it/", listOf()))
                }
            }),
            bottomSheetState = BottomSheetState(initialValue = BottomSheetValue.SemiExpanded),
            onTermClick = {}
        ) { }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Preview
@Composable
fun TermSearchBottomSheetDarkPreview() {
    AppTheme(useDarkTheme = true) {
        TermSearchBottomSheetScaffold(
            bottomSheetContentState = ScreenState.Results("Word", buildList {
                repeat(10) {
                    add(TermItem(0L, "Word $it", "/$it/", listOf()))
                }
            }),
            bottomSheetState = BottomSheetState(initialValue = BottomSheetValue.SemiExpanded),
            onTermClick = {}
        ) { }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Preview
@Composable
fun TermSearchBottomSheetLoadingLightPreview() {
    AppTheme(useDarkTheme = false) {
        TermSearchBottomSheetScaffold(
            bottomSheetContentState = ScreenState.Loading("Word"),
            bottomSheetState = BottomSheetState(initialValue = BottomSheetValue.SemiExpanded),
            onTermClick = {}
        ) { }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Preview
@Composable
fun TermSearchBottomSheetLoadingDarkPreview() {
    AppTheme(useDarkTheme = true) {
        TermSearchBottomSheetScaffold(
            bottomSheetContentState = ScreenState.Loading("Word"),
            bottomSheetState = BottomSheetState(initialValue = BottomSheetValue.SemiExpanded),
            onTermClick = {}
        ) { }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Preview
@Composable
fun TermSearchBottomSheetNoResultsLightPreview() {
    AppTheme(useDarkTheme = false) {
        TermSearchBottomSheetScaffold(
            bottomSheetContentState = ScreenState.NoResults("Word"),
            bottomSheetState = BottomSheetState(initialValue = BottomSheetValue.SemiExpanded),
            onTermClick = {}
        ) { }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Preview
@Composable
fun TermSearchBottomSheetNoResultsDarkPreview() {
    AppTheme(useDarkTheme = true) {
        TermSearchBottomSheetScaffold(
            bottomSheetContentState = ScreenState.NoResults("Word"),
            bottomSheetState = BottomSheetState(initialValue = BottomSheetValue.SemiExpanded),
            onTermClick = {}
        ) { }
    }
}
