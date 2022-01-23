package alektas.common.ui

import alektas.common.R
import alektas.common.ui.models.DefinitionItem
import alektas.common.ui.models.TermItem
import alektas.midic.theme.*
import alektas.ui_components.card.ItemCard
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TermCard(
    modifier: Modifier = Modifier,
    item: TermItem,
    showDefinitionCount: Boolean = true,
    imagePainter: Painter = painterResource(id = R.drawable.ic_term_image_placeholder),
    onItemClick: (TermItem) -> Unit = {}
) {
    ItemCard(
        modifier = modifier
            .height(100.dp)
            .clickable { onItemClick(item) },
        imagePainter = imagePainter,
        bodyText = item.word,
        firstLabel = item.transcription,
        secondLabel = if (showDefinitionCount) "${item.definitions.count()} definitions" else null,
        trailing = {
            Image(
                modifier = Modifier.size(sizeIconM),
                painter = painterResource(id = R.drawable.ic_details),
                contentDescription = "Term clickable icon"
            )
        }
    )
}

@Preview
@Composable
fun TermCardLightPreview() {
    AppTheme(useDarkTheme = false) {
        TermCard(
            item = getPreviewItem(),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview
@Composable
fun TermCardLightTwoLinePreview() {
    AppTheme(useDarkTheme = false) {
        TermCard(
            item = getPreviewItem(),
            modifier = Modifier.fillMaxWidth(),
            showDefinitionCount = false,
        )
    }
}

@Preview
@Composable
fun TermCardDarkPreview() {
    AppTheme(useDarkTheme = true) {
        val definitions = buildList {
            repeat(10) {
                add(
                    DefinitionItem(
                        id = it.toLong(),
                        partOfSpeech = "noun",
                        "Definition $it",
                        "Example $it",
                    )
                )
            }
        }
        TermCard(item = getPreviewItem(definitions), modifier = Modifier.fillMaxWidth())
    }
}

private fun getPreviewItem(definitions: List<DefinitionItem> = listOf()) = TermItem(
    id = 0L,
    word = "Word",
    transcription = "/word/",
    definitions = definitions
)
