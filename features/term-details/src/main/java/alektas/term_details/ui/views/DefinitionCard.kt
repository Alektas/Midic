package alektas.term_details.ui.views

import alektas.common.ui.models.DefinitionItem
import alektas.common.ui.utils.generateDefinitionItem
import alektas.midic.theme.*
import alektas.term_details.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource

@Composable
fun DefinitionCard(
    item: DefinitionItem,
    modifier: Modifier = Modifier,
    imagePainter: Painter = painterResource(id = R.drawable.ic_term_image_placeholder),
    onClick: (Click) -> Unit
) = with(item) {
    Card(
        shape = RoundedCornerShape(cornersM),
        elevation = cardElevation,
        modifier = modifier
    ) {
        Column(verticalArrangement = Arrangement.SpaceBetween) {
            Column {
                Spacer(Modifier.height(paddingM))
                Image(
                    painter = imagePainter,
                    contentDescription = null,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                        .size(sizeIconX40)
                )
                Spacer(Modifier.height(paddingM))
                Text(
                    text = "[$partOfSpeech]",
                    style = MaterialTheme.typography.caption,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = paddingM)
                )
                Spacer(Modifier.height(paddingM))
                Text(
                    text = definition,
                    style = MaterialTheme.typography.body2,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = paddingM)
                )
                Spacer(Modifier.height(paddingM))
                if (example != null) {
                    Text(
                        text = "\"$example\"",
                        style = MaterialTheme.typography.caption,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = paddingM)
                    )
                }
                Spacer(Modifier.height(paddingM))
            }
            ButtonPanel(inBookmarks, onClick = onClick)
        }
    }
}

@Composable
private fun ButtonPanel(
    inBookmarks: Boolean,
    modifier: Modifier = Modifier,
    onClick: (Click) -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        IconButton(onClick = { onClick(Click.Share) }) {
            Icon(Icons.Outlined.Share, stringResource(R.string.descr_definition_share))
        }
        IconButton(onClick = { onClick(Click.Copy) }) {
            Icon(Icons.Outlined.ContentCopy, stringResource(R.string.descr_definition_copy))
        }
        IconButton(onClick = { onClick(Click.Bookmark) }) {
            Icon(
                if (inBookmarks) Icons.Outlined.Bookmark else Icons.Outlined.BookmarkBorder,
                stringResource(R.string.descr_save_to_bookmarks)
            )
        }
    }
}

enum class Click { Share, Copy, Bookmark }

@Composable
@Preview
fun DefinitionCardPreview() {
    AppTheme(useDarkTheme = false) {
        DefinitionCard(
            item = generateDefinitionItem(),
            modifier = Modifier.height(1000.dp)
        ) {

        }
    }
}
