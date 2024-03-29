package alektas.term_search.ui.views

import alektas.midic.theme.*
import alektas.term_search.R
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActionScope
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    query: String = "",
    hint: String = "",
    withVoiceInputButton: Boolean = false,
    onVoiceInputClick: () -> Unit = {},
    onKeyboardDoneClick: KeyboardActionScope.() -> Unit = {},
    onQueryChanged: (String) -> Unit
) {
    Surface(
        modifier = modifier.requiredHeight(48.dp),
    ) {
        Row(
            modifier = Modifier.padding(start = paddingX4, end = paddingX1),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            BasicTextField(
                value = query,
                onValueChange = {
                    onQueryChanged(it)
                },
                singleLine = true,
                textStyle = MaterialTheme.typography.bodyLarge.copy(
                    color = MaterialTheme.colorScheme.onBackground
                ),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = onKeyboardDoneClick),
                decorationBox = { input ->
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Outlined.Search,
                            contentDescription = null,
                        )
                        Spacer(modifier = Modifier.size(paddingX2))
                        if (query.isEmpty()) {
                            Text(hint, color = MaterialTheme.colorScheme.onSurface)
                        } else {
                            input()
                        }
                    }
                },
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
                    .alignByBaseline()
            )

            if (withVoiceInputButton) {
                IconButton(onClick = { onVoiceInputClick() }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_mic),
                        contentDescription = stringResource(R.string.descr_btn_voice_search),
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun SearchBarPreview() {
    AppTheme(useDarkTheme = false) {
        SearchBar(query = "Search query") { }
    }
}

@Preview
@Composable
fun SearchBarDarkPreview() {
    AppTheme(useDarkTheme = true) {
        SearchBar(query = "Search query") { }
    }
}