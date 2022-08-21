package alektas.common.ui.utils

import alektas.common.R
import alektas.common.ui.models.DefinitionItem
import alektas.common.ui.models.TermItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import coil.compose.rememberImagePainter
import coil.request.ImageRequest

@Composable
fun TermItem.rememberPrimaryImagePainter(): Painter = rememberImagePainter(
    data = getPrimaryImageUrl(),
    builder = getImageRequestBuilder()
)

private fun TermItem.getPrimaryImageUrl(): String? =
    definitions.firstOrNull { it.imageUrl != null }?.imageUrl

@Composable
fun DefinitionItem.rememberImagePainter(): Painter = rememberImagePainter(
    data = imageUrl,
    builder = getImageRequestBuilder()
)

private fun getImageRequestBuilder(): ImageRequest.Builder.() -> Unit = {
    crossfade(true)
    placeholder(R.drawable.ic_term_image_placeholder)
    fallback(R.drawable.ic_term_image_placeholder)
}
