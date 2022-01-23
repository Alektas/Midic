package alektas.ui_components.card

import alektas.midic.theme.cardElevation
import alektas.midic.theme.cornersM
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun MidicCard(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(corner = CornerSize(cornersM)),
        backgroundColor = MaterialTheme.colorScheme.surface,
        elevation = cardElevation,
        content = content
    )
}
