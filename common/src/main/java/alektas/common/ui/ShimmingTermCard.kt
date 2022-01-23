package alektas.common.ui

import alektas.common.R
import alektas.ui_components.card.ItemCard
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource

// TODO: add shimming
@Composable
fun ShimmingTermCard(
    modifier: Modifier = Modifier,
    imagePainter: Painter = painterResource(id = R.drawable.ic_term_image_placeholder),
    firstLine: (@Composable () -> Unit)? = null,
    secondLine: (@Composable () -> Unit)? = null,
    thirdLine: (@Composable () -> Unit)? = null,
) {
    ItemCard(
        modifier = modifier
            .height(TermCardDefaults.heightCard),
        leading = {
            Image(
                modifier = Modifier
                    .size(TermCardDefaults.sizeIcon)
                    .clip(CircleShape),
                painter = imagePainter,
                contentScale = ContentScale.Crop,
                contentDescription = "Term shimming image"
            )
        },
        firstLine = firstLine,
        secondLine = secondLine,
        thirdLine = thirdLine,
        trailing = null,
        onItemClick = { }
    )
}