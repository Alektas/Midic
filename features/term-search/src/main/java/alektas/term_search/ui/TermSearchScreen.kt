package alektas.term_search.ui

import alektas.term_search.ui.models.ScreenAction
import alektas.term_search.ui.models.ScreenEvent
import alektas.term_search.ui.models.ScreenState
import alektas.term_search.ui.views.SearchBar
import alektas.term_search.ui.views.TermSearchBottomSheetScaffold
import alektas.ui_components.bottom_sheet.BottomSheetValue
import alektas.ui_components.bottom_sheet.rememberBottomSheetState
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext

@OptIn(ExperimentalMaterialApi::class, androidx.compose.material3.ExperimentalMaterial3Api::class)
@Composable
fun TermSearchScreen(viewModel: TermSearchViewModel) {
    val state by viewModel.screenState.collectAsState()
    Log.d("TermSearchScreen", "state = $state")

    val bottomSheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Hidden)

    LaunchedEffect(state) {
        Log.d("TermSearchScreen", "TermSearchScreen: launch sheet hiding")
        if (state is ScreenState.NoResults) {
            bottomSheetState.hide()
        } else {
            bottomSheetState.expand()
        }
    }

    val context = LocalContext.current
    LaunchedEffect(Unit) {
        viewModel.screenEvents.collect { event ->
            Log.d("TermSearchScreen", "event = $event")
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
        TermSearchBottomSheetScaffold(
            modifier = Modifier.weight(1.0f), // without weight bottom sheet pushes out content below it
            bottomSheetContentState = state,
            bottomSheetState = bottomSheetState,
            onTermClick = { term ->
                viewModel.onAction(ScreenAction.TermSelection(term))
            }
        ) {
            // background content
        }
        SearchBar(
            query = state.searchQuery,
            onQueryChanged = { query ->
                viewModel.onAction(ScreenAction.Query(query))
            }
        )
    }
}