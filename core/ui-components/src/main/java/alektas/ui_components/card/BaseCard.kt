package alektas.ui_components.card

import alektas.midic.theme.cardElevation
import alektas.midic.theme.cornersX4
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BaseCard(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    content: @Composable () -> Unit,
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(corner = CornerSize(cornersX4)),
        backgroundColor = MaterialTheme.colorScheme.surfaceVariant,
        elevation = cardElevation,
        onClick = onClick,
        content = content
    )
}
