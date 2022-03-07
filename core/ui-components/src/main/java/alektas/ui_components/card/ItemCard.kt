package alektas.ui_components.card

import alektas.midic.theme.paddingX4
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun ItemCard(
    modifier: Modifier = Modifier,
    leading: (@Composable () -> Unit)? = null,
    firstLine: (@Composable () -> Unit)? = null,
    secondLine: (@Composable () -> Unit)? = null,
    thirdLine: (@Composable () -> Unit)? = null,
    trailing: (@Composable () -> Unit)? = null,
    onItemClick: () -> Unit = {},
) {
    BaseCard(
        modifier = modifier,
        onClick = onItemClick
    ) {
        Row(
            modifier = Modifier.padding(horizontal = paddingX4),
            verticalAlignment = Alignment.CenterVertically
        ) {
            leading?.let {
                it()
                Spacer(modifier = Modifier.size(paddingX4))
            }
            Column(
                modifier = Modifier.weight(1.0f),
                verticalArrangement = Arrangement.Center,
            ) {
                firstLine?.invoke()
                secondLine?.invoke()
                thirdLine?.invoke()
            }
            trailing?.let {
                Spacer(modifier = Modifier.size(paddingX4))
                trailing()
            }
        }
    }
}