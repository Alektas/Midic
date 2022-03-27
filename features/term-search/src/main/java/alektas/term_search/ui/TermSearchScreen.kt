package alektas.term_search.ui

import alektas.term_search.R
import alektas.term_search.ui.models.ScreenAction
import alektas.term_search.ui.models.ScreenEvent
import alektas.term_search.ui.models.ScreenState
import alektas.term_search.ui.views.SearchBar
import alektas.term_search.ui.views.TermSearchBottomSheetScaffold
import alektas.ui_components.app_bar.TopAppBar
import alektas.ui_components.bottom_sheet.BottomSheetValue
import alektas.ui_components.bottom_sheet.rememberBottomSheetState
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource

@OptIn(ExperimentalMaterialApi::class, androidx.compose.material3.ExperimentalMaterial3Api::class)
@Composable
fun TermSearchScreen(
    viewModel: TermSearchViewModel,
    onBookmarksClick: () -> Unit,
    backgroundContent: @Composable (PaddingValues) -> Unit
) {
    val state by viewModel.screenState.collectAsState()
    val bottomSheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Hidden)

    LaunchedEffect(state) {
        if (state is ScreenState.NoResults) {
            bottomSheetState.hide()
        } else {
            bottomSheetState.expand()
        }
    }

    val context = LocalContext.current
    LaunchedEffect(Unit) {
        viewModel.screenEvents.collect { event ->
            when (event) {
                ScreenEvent.CollapseSearchResults -> bottomSheetState.collapse()
                is ScreenEvent.Error -> {
                    Toast.makeText(context, event.message, Toast.LENGTH_LONG).show() // TODO
                }
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom
    ) {
        AppBar(onBookmarksClick)
        TermSearchBottomSheetScaffold(
            modifier = Modifier.weight(1.0f), // without weight bottom sheet pushes out content below it
            bottomSheetContentState = state,
            bottomSheetState = bottomSheetState,
            onTermClick = { term ->
                viewModel.onAction(ScreenAction.TermSelection(term))
            }
        ) { paddings ->
            backgroundContent(paddings)
        }
        SearchBar(
            query = state.searchQuery,
            onQueryChanged = { query ->
                viewModel.onAction(ScreenAction.Query(query))
            }
        )
    }
}

@Composable
fun AppBar(
    onBookmarksClick: () -> Unit
) {
    TopAppBar(
        actions = {
            IconButton(onClick = onBookmarksClick) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_bookmarks),
                    contentDescription = stringResource(id = R.string.descr_btn_bookmarks)
                )
            }
        }
    )
}