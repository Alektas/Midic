package alektas.term_details.ui.views

import alektas.term_details.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun InitBackground(
    modifier: Modifier = Modifier,
) {
    SearchBackground(
        modifier = modifier,
        header = { DefaultSearchBackgroundHeader(painterResource(id = R.drawable.ic_magnifier)) }
    ) {
        Text(
            text = stringResource(id = R.string.search_init_hint),
            style = MaterialTheme.typography.bodySmall,
        )
        Image(
            modifier = Modifier.fillMaxHeight(),
            painter = painterResource(id = R.drawable.ic_arrow_hint),
            contentScale = ContentScale.FillBounds,
            contentDescription = null,
        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun SearchInitBackgroundPreview() {
    InitBackground(
        modifier = Modifier.fillMaxSize()
    )
}
