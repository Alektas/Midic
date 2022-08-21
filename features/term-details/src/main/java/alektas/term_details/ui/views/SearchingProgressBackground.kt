package alektas.term_details.ui.views

import alektas.term_details.R
import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun SearchingProgressBackground(
    modifier: Modifier = Modifier,
) {
    SearchBackground(
        modifier = modifier,
        header = { HeaderImage() }
    ) {
        Text(
            text = stringResource(id = R.string.search_progress_hint),
            style = MaterialTheme.typography.bodySmall,
        )
    }
}

@Composable
private fun ColumnScope.HeaderImage() {
    val animation = rememberInfiniteTransition()
    val xOffset by animation.animateOffset(angleEasing = ::cos)
    val yOffset by animation.animateOffset(angleEasing = ::sin)
    DefaultSearchBackgroundHeader(
        painter = painterResource(id = R.drawable.ic_magnifier),
        modifier = Modifier.offset(x = xOffset.dp, y = (yOffset - Defaults.IMAGE_ANIMATION_RADIUS).dp)
    )
}

@Composable
private fun InfiniteTransition.animateOffset(angleEasing: (Double) -> Double): State<Float> =
    animateFloat(
        initialValue = 0f,
        targetValue = Defaults.IMAGE_ANIMATION_RADIUS,
        animationSpec = InfiniteRepeatableSpec(
            animation = tween(
                durationMillis = Defaults.IMAGE_ANIMATION_DURATION,
                easing = { fraction ->
                    angleEasing(fractionToRadian(fraction)).toFloat()
                }
            ),
            repeatMode = RepeatMode.Restart,
        ),
    )

private fun fractionToRadian(fraction: Float): Double = PI * (0.5f + 2 * fraction)

private object Defaults {
    const val IMAGE_ANIMATION_DURATION = 2_000
    const val IMAGE_ANIMATION_RADIUS = 30f
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun SearchingBackgroundPreview() {
    SearchingProgressBackground(
        modifier = Modifier.fillMaxSize()
    )
}
