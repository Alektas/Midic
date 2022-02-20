package alektas.term_details.ui.views

import alektas.common.ui.models.DefinitionItem
import alektas.common.ui.utils.generateDefinitionItem
import alektas.midic.theme.paddingXl
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ScaleFactor
import androidx.compose.ui.layout.lerp
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import kotlin.math.absoluteValue

@OptIn(ExperimentalPagerApi::class)
@Composable
fun DefinitionsCarousel(
    definitions: List<DefinitionItem>,
    modifier: Modifier = Modifier,
    onClick: (Click) -> Unit
) {
    HorizontalPager(
        count = definitions.size,
        contentPadding = PaddingValues(horizontal = paddingXl),
        modifier = modifier
    ) { page ->
        DefinitionCard(
            item = definitions[page],
            onClick = onClick,
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer {
                    // Calculate the absolute offset for the current page from the
                    // scroll position. Absolute value allows to mirror
                    // any effects for both directions
                    val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue

                    lerp(
                        start = ScaleFactor(scaleX = 0.95f, scaleY = 0.95f),
                        stop = ScaleFactor(scaleX = 1f, scaleY = 1f),
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    ).also { scale ->
                        scaleX = scale.scaleX
                        scaleY = scale.scaleY

                        alpha = scaleY
                    }
                }
        )
    }
}

@Preview
@Composable
fun DefinitionsCarouselPreview() {
    val definitions = List(10) {
        generateDefinitionItem()
    }
    DefinitionsCarousel(definitions = definitions, onClick = {})
}
