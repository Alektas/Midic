package alektas.bookmark_list.ui

import alektas.bookmark_list.BookmarkListViewModel
import alektas.bookmark_list.ui.models.Action
import alektas.bookmark_list.ui.models.Event
import alektas.bookmark_list.ui.models.UiState
import alektas.bookmark_list.views.BookmarksPlaceholder
import alektas.common.ui.ShimmingTermCard
import alektas.common.ui.TermCard
import alektas.common.ui.models.TermItem
import alektas.common.ui.utils.rememberPrimaryImagePainter
import alektas.midic.theme.AppTheme
import alektas.midic.theme.paddingX2
import alektas.midic.theme.paddingX4
import alektas.ui_components.app_bar.TopAppBar
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterialApi::class, androidx.compose.material3.ExperimentalMaterial3Api::class)
@Composable
fun BookmarkListScreen(
    viewModel: BookmarkListViewModel,
    onBackButtonClick: () -> Unit,
) {
    LaunchedEffect(Unit) {
        viewModel.screenEvents.collect { event ->
            when (event) {
                is Event.ShowDefinitions -> {
                    // TODO()
                }
            }
        }
    }

    Column {
        TopAppBar(backBtnCallback = onBackButtonClick)
        val state by viewModel.screenState.collectAsState()
        BookmarkList(
            state = state,
            onTermClick = { viewModel.onAction(Action.TermSelection(it)) }
        )
    }
}

@Composable
private fun BookmarkList(
    state: UiState,
    modifier: Modifier = Modifier,
    onTermClick: (TermItem) -> Unit,
) {
    when (state) {
        is UiState.Loading -> List(modifier) {
            items(1) {
                ShimmingTermCard()
                Spacer(modifier = Modifier.size(paddingX2))
            }
        }
        is UiState.Placeholder -> BookmarksPlaceholder(modifier)
        is UiState.Bookmarks -> List(modifier) {
            items(state.bookmarks) { term ->
                TermCard(
                    term = term,
                    modifier = Modifier.fillMaxWidth(),
                    imagePainter = term.rememberPrimaryImagePainter(),
                    onItemClick = onTermClick,
                )
                Spacer(modifier = Modifier.size(paddingX2))
            }
        }
    }
}

@Composable
private fun List(
    modifier: Modifier = Modifier,
    content: LazyListScope.() -> Unit,
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = paddingX4),
        content = content
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Preview
@Composable
fun BookmarkListLightPreview() {
    AppTheme(useDarkTheme = false) {
        BookmarkList(
            state = UiState.Bookmarks(buildList {
                repeat(10) {
                    add(TermItem("Word $it", "/$it/", listOf()))
                }
            }),
        ) { }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Preview
@Composable
fun BookmarkListDarkPreview() {
    AppTheme(useDarkTheme = true) {
        BookmarkList(
            state = UiState.Bookmarks(buildList {
                repeat(10) {
                    add(TermItem("Word $it", "/$it/", listOf()))
                }
            }),
            onTermClick = {}
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Preview
@Composable
fun BookmarkListLoadingLightPreview() {
    AppTheme(useDarkTheme = false) {
        BookmarkList(
            state = UiState.Loading,
            onTermClick = {}
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Preview
@Composable
fun BookmarkListLoadingDarkPreview() {
    AppTheme(useDarkTheme = true) {
        BookmarkList(
            state = UiState.Loading,
            onTermClick = {}
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Preview
@Composable
fun BookmarkListNoResultsLightPreview() {
    AppTheme(useDarkTheme = false) {
        BookmarkList(
            state = UiState.Placeholder,
            onTermClick = {}
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Preview
@Composable
fun BookmarkListNoResultsDarkPreview() {
    AppTheme(useDarkTheme = true) {
        BookmarkList(
            state = UiState.Placeholder,
            onTermClick = {}
        )
    }
}
