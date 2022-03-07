package alektas.term_details.ui.views

import alektas.midic.theme.sizeIconX32
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun SearchBackground(
    headerImage: Painter,
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Image(
            painter = headerImage,
            contentDescription = null,
            modifier = Modifier
                .size(sizeIconX32)
                .weight(SearchBackgroundDefaults.headerImageWeight)
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier.weight(SearchBackgroundDefaults.contentWeight)
                .fillMaxSize(),
            content = content
        )
    }
}

private object SearchBackgroundDefaults {
    const val headerImageWeight = 2f
    const val contentWeight = 1f
}
