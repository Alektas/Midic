package alektas.bookmark_list.views

import alektas.bookmark_list.R
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun BookmarksPlaceholder(
    modifier: Modifier = Modifier,
) {
    ScreenPlaceholder(
        modifier = modifier,
        header = { DefaultScreenPlaceholderHeader(painterResource(id = R.drawable.ic_sad)) }
    ) {
        Text(
            text = stringResource(id = R.string.placeholder_bookmarks),
            style = MaterialTheme.typography.bodySmall,
        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun BookmarksPlaceholderPreview() {
    BookmarksPlaceholder(
        modifier = Modifier.fillMaxSize()
    )
}
