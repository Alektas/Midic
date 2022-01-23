package alektas.term_search.ui

import alektas.common.ui.TermCard
import alektas.common.ui.models.TermItem
import alektas.midic.theme.*
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
fun TermSearchBottomSheet(
    modifier: Modifier = Modifier,
    terms: List<TermItem>,
    bottomSheetState: BottomSheetState = rememberBottomSheetState(BottomSheetValue.Collapsed),
    onTermClick: (TermItem) -> Unit,
    content: @Composable (PaddingValues) -> Unit,
) {
    BottomSheetScaffold(
        modifier = modifier,
        content = content,
        sheetShape = RoundedCornerShape(topStart = cornersXl, topEnd = cornersXl),
        bottomSheetState = bottomSheetState,
        sheetExpandedStateEnabled = true,
        sheetBackgroundColor = MaterialTheme.colorScheme.surfaceVariant,
        sheetElevation = 0.dp,
        sheetPeekHeight = badgeHeight + paddingXs * 2,
        sheetContent = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(modifier = Modifier.size(paddingXs))
                BottomSheetBadge()
                Spacer(modifier = Modifier.size(paddingXs))
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(horizontal = paddingM)
                ) {
                    items(terms) { term ->
                        TermCard(
                            modifier = Modifier.fillMaxWidth(),
                            term = term,
                            onItemClick = onTermClick,
                        )
                        Spacer(modifier = Modifier.size(paddingXs))
                    }
                }
            }
        },
    )
}

@Composable
private fun BottomSheetBadge() {
    Box(
        modifier = Modifier
            .height(badgeHeight)
            .width(56.dp)
            .clip(RoundedCornerShape(cornersXxs))
            .background(MaterialTheme.colorScheme.tertiary)
    )
}

private val badgeHeight = 6.dp

@OptIn(ExperimentalMaterialApi::class)
@Preview
@Composable
fun TermSearchBottomSheetLightPreview() {
    AppTheme(useDarkTheme = false) {
        TermSearchBottomSheet(
            terms = buildList {
                repeat(10) {
                    add(TermItem(0L, "Word $it", "/$it/", listOf()))
                }
            },
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
        TermSearchBottomSheet(
            terms = buildList {
                repeat(10) {
                    add(TermItem(0L, "Word $it", "/$it/", listOf()))
                }
            },
            bottomSheetState = BottomSheetState(initialValue = BottomSheetValue.SemiExpanded),
            onTermClick = {}
        ) { }
    }
}
