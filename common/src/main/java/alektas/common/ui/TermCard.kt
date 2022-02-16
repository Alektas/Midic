package alektas.common.ui

import alektas.common.R
import alektas.common.ui.models.DefinitionItem
import alektas.common.ui.models.TermItem
import alektas.common.ui.utils.pluralResource
import alektas.midic.theme.*
import alektas.ui_components.card.ItemCard
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TermCard(
    modifier: Modifier = Modifier,
    term: TermItem,
    showDefinitionCount: Boolean = true,
    imagePainter: Painter = painterResource(id = R.drawable.ic_term_image_placeholder),
    onItemClick: (TermItem) -> Unit = {}
) {
    val definitionsCount = if (showDefinitionCount) {
        pluralResource(
            resId = R.plurals.plural_term_definitions_count,
            quantity = term.definitions.count(),
            formatArgs = arrayOf(term.definitions.count())
        )
    } else {
        null
    }
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
                contentDescription = "Term image"
            )
        },
        firstLine = {
            Text(
                text = term.word,
                maxLines = 1,
                style = MaterialTheme.typography.bodyMedium,
            )
        },
        secondLine = {
            Text(
                text = term.transcription,
                maxLines = 1,
                style = MaterialTheme.typography.labelMedium,
            )
        },
        thirdLine = definitionsCount?.let {
            {
                Text(
                    text = definitionsCount,
                    maxLines = 1,
                    style = MaterialTheme.typography.labelMedium,
                )
            }
        },
        trailing = {
            Image(
                modifier = Modifier.size(sizeIconM),
                painter = painterResource(id = R.drawable.ic_details),
                contentDescription = stringResource(id = R.string.descr_btn_term_details)
            )
        },
        onItemClick = { onItemClick(term) }
    )
}

object TermCardDefaults {
    val heightCard = 100.dp
    val sizeIcon = sizeIconL
}

@Preview
@Composable
fun TermCardLightPreview() {
    AppTheme(useDarkTheme = false) {
        TermCard(
            term = getPreviewItem(),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview
@Composable
fun TermCardLightTwoLinePreview() {
    AppTheme(useDarkTheme = false) {
        TermCard(
            term = getPreviewItem(),
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
                        definition = "Definition $it",
                        example = "Example $it",
                        inBookmarks = false,
                    )
                )
            }
        }
        TermCard(term = getPreviewItem(definitions), modifier = Modifier.fillMaxWidth())
    }
}

private fun getPreviewItem(definitions: List<DefinitionItem> = listOf()) = TermItem(
    id = 0L,
    word = "Word",
    transcription = "/word/",
    definitions = definitions
)
