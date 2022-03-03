package alektas.term_details.ui.views

import alektas.midic.theme.sizeIconX32
import alektas.term_details.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun SearchResultsHintBackground(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_glasses),
            contentDescription = null,
            modifier = Modifier.size(sizeIconX32)
                .weight(1f)
        )
        Text(
            text = stringResource(id = R.string.search_results_hint),
            style = MaterialTheme.typography.bodySmall,
        )
        Image(
            painter = painterResource(id = R.drawable.ic_arrow_hint),
            contentScale = ContentScale.FillBounds,
            contentDescription = null,
            modifier = Modifier.weight(1f)
        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun SearchResultsHintBackgroundPreview() {
    SearchResultsHintBackground(
        modifier = Modifier.fillMaxSize()
    )
}
