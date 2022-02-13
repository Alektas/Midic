package alektas.common.ui.utils

import alektas.common.R
import alektas.common.ui.models.TermItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import coil.compose.rememberImagePainter

@Composable
fun TermItem.getPrimaryImage(): Painter = rememberImagePainter(
    data = getPrimaryImageUrl(),
    builder = {
        crossfade(true)
        placeholder(R.drawable.ic_term_image_placeholder)
        fallback(R.drawable.ic_term_image_placeholder)
    }
)

fun TermItem.getPrimaryImageUrl(): String? =
    definitions.firstOrNull { it.imageUrl != null }?.imageUrl
