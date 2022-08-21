package alektas.term_details.ui.views

import alektas.midic.theme.sizeIconX32
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter

@Composable
fun SearchBackground(
    modifier: Modifier = Modifier,
    header: @Composable ColumnScope.() -> Unit,
    content: @Composable ColumnScope.() -> Unit,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        header()
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .weight(SearchBackgroundDefaults.contentWeight)
                .fillMaxSize(),
            content = content
        )
    }
}

@Composable
fun ColumnScope.DefaultSearchBackgroundHeader(
    painter: Painter,
    modifier: Modifier = Modifier,
) {
    Image(
        painter = painter,
        contentDescription = null,
        modifier = modifier
            .size(sizeIconX32)
            .weight(SearchBackgroundDefaults.headerImageWeight)
    )
}

private object SearchBackgroundDefaults {
    const val headerImageWeight = 2f
    const val contentWeight = 1f
}
