package alektas.term_details.ui

import alektas.common.ui.models.TermItem
import alektas.midic.theme.paddingX2
import alektas.midic.theme.paddingX4
import alektas.term_details.R
import alektas.term_details.ui.models.Action
import alektas.term_details.ui.models.Event
import alektas.term_details.ui.models.UiState
import alektas.term_details.ui.views.*
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString

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

    val context = LocalContext.current
    val clipboard = LocalClipboardManager.current
    LaunchedEffect(Unit) {
        viewModel.events.collect { event ->
            when (event) {
                is Event.CopyToClipboard -> clipboard.copy(context, event.text)
                is Event.Share -> shareContent(context, event.text)
            }
        }
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
        if (term.transcription.isNotBlank()) {
            Text(
                text = "/${term.transcription}/",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface,
            )
        }
        Spacer(modifier = Modifier.height(paddingX4))
        DefinitionsCarousel(
            definitions = term.definitions,
            onClick = onClick
        )
    }
}

private fun ClipboardManager.copy(context: Context, text: String) {
    setText(AnnotatedString(text))
    Toast.makeText(
        context,
        context.getString(R.string.hint_definition_is_copied),
        Toast.LENGTH_SHORT
    ).show()
}

private fun shareContent(context: Context, text: String) {
    val intent = Intent(Intent.ACTION_SEND).run {
        putExtra(Intent.EXTRA_TEXT, text)
        type = "text/plain"
        Intent.createChooser(this, null)
    }
    context.startActivity(intent)
}
