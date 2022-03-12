package alektas.term_details.ui.views

import alektas.common.ui.models.DefinitionItem
import alektas.common.ui.utils.generateDefinitionItem
import alektas.midic.theme.*
import alektas.term_details.R
import alektas.term_details.ui.models.Action
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
    onClick: (Action) -> Unit
) = with(item) {
    Card(
        shape = RoundedCornerShape(cornersX4),
        elevation = cardElevation,
        modifier = modifier
    ) {
        Column(verticalArrangement = Arrangement.SpaceBetween) {
            Column {
                Spacer(Modifier.height(paddingX4))
                Image(
                    painter = imagePainter,
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .size(sizeIconX40)
                )
                Spacer(Modifier.height(paddingX4))
                Text(
                    text = "[$partOfSpeech]",
                    style = MaterialTheme.typography.caption,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = paddingX4)
                )
                Spacer(Modifier.height(paddingX4))
                Text(
                    text = definition,
                    style = MaterialTheme.typography.body2,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = paddingX4)
                )
                Spacer(Modifier.height(paddingX4))
                if (example != null) {
                    Text(
                        text = "\"$example\"",
                        style = MaterialTheme.typography.caption,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = paddingX4)
                    )
                }
                Spacer(Modifier.height(paddingX4))
            }
            ButtonPanel(item, onClick = onClick)
        }
    }
}

@Composable
private fun ButtonPanel(
    definition: DefinitionItem,
    modifier: Modifier = Modifier,
    onClick: (Action) -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        IconButton(onClick = { onClick(Action.Share(definition)) }) {
            Icon(Icons.Outlined.Share, stringResource(R.string.descr_definition_share))
        }
        IconButton(onClick = { onClick(Action.Copy(definition)) }) {
            Icon(Icons.Outlined.ContentCopy, stringResource(R.string.descr_definition_copy))
        }
        IconButton(onClick = { onClick(Action.Bookmark(definition)) }) {
            Icon(
                if (definition.inBookmarks) Icons.Outlined.Bookmark else Icons.Outlined.BookmarkBorder,
                stringResource(R.string.descr_save_to_bookmarks)
            )
        }
    }
}

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
