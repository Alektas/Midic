package alektas.term_details.ui.views

import alektas.midic.theme.paddingX2
import alektas.term_details.R
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun SearchErrorBackground(
    modifier: Modifier = Modifier,
    onRetryClick: () -> Unit,
) {
    SearchBackground(
        modifier = modifier,
        headerImage = painterResource(id = R.drawable.ic_sad)
    ) {
        Text(
            text = stringResource(id = R.string.search_error_hint),
            style = MaterialTheme.typography.bodySmall,
        )
        TextButton(
            onClick = onRetryClick
        ) {
            Icon(painter = painterResource(id = R.drawable.ic_refresh), contentDescription = null)
            Spacer(modifier = Modifier.size(paddingX2))
            Text(text = stringResource(id = R.string.btn_try_again))
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun SearchErrorBackgroundPreview() {
    SearchErrorBackground(
        modifier = Modifier.fillMaxSize()
    ) {

    }
}
