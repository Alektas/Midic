package alektas.ui_components.card

import alektas.midic.theme.paddingM
import alektas.midic.theme.sizeIconL
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale

@Composable
fun ItemCard(
    modifier: Modifier = Modifier,
    imagePainter: Painter? = null,
    bodyText: String,
    firstLabel: String? = null,
    secondLabel: String? = null,
    trailing: (@Composable () -> Unit)? = null,
) {
    MidicCard(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.padding(horizontal = paddingM),
            verticalAlignment = Alignment.CenterVertically
        ) {
            imagePainter?.let {
                Image(
                    modifier = Modifier
                        .size(sizeIconL)
                        .clip(CircleShape),
                    painter = imagePainter,
                    contentScale = ContentScale.Crop,
                    contentDescription = "Term image"
                )
                Spacer(modifier = Modifier.size(paddingM))
            }
            Column(
                modifier = Modifier.weight(1.0f),
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = bodyText,
                    maxLines = 1,
                    style = MaterialTheme.typography.bodyMedium,
                )
                firstLabel?.let {
                    Text(
                        text = firstLabel,
                        maxLines = 1,
                        style = MaterialTheme.typography.labelMedium,
                    )
                }
                secondLabel?.let {
                    Text(
                        text = secondLabel,
                        maxLines = 1,
                        style = MaterialTheme.typography.labelMedium,
                    )
                }
            }
            trailing?.let {
                Spacer(modifier = Modifier.size(paddingM))
                trailing()
            }
        }
    }
}