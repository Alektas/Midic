package alektas.term_details.ui

import alektas.common.ui.models.TermItem
import alektas.midic.theme.paddingX2
import alektas.midic.theme.paddingX4
import alektas.term_details.ui.models.Action
import alektas.term_details.ui.models.UiState
import alektas.term_details.ui.views.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun SelectedTermScreen(
    viewModel: TermViewModel,
    modifier: Modifier = Modifier
) {
    val state by viewModel.uiState.collectAsState()
    when (state) {
        is UiState.Init -> InitBackground(modifier)
        is UiState.Searching -> SearchingProgressBackground(modifier)
        is UiState.TermNotSelected -> TermNotSelectedBackground(modifier)
        is UiState.Error -> ErrorBackground(
            modifier = modifier,
            onRetryClick = { viewModel.onAction(Action.Retry) }
        )
        is UiState.NothingFound -> NothingFoundBackground(
            modifier = modifier,
            onGetRandomClick = { viewModel.onAction(Action.GetRandomTerm) }
        )
        is UiState.Term -> TermComponent(
            term = (state as UiState.Term).term,
            modifier = modifier,
            onClick = { viewModel.onAction(it) }
        )
    }
}

@Composable
private fun TermComponent(
    term: TermItem,
    modifier: Modifier = Modifier,
    onClick: (Action) -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Spacer(modifier = Modifier.height(paddingX2))
        Text(
            text = term.word,
            style = MaterialTheme.typography.displaySmall
        )
        Text(
            text = "/${term.transcription}/",
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(paddingX4))
        DefinitionsCarousel(
            definitions = term.definitions,
            onClick = onClick
        )
    }
}
