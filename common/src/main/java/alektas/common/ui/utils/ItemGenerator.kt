package alektas.common.ui.utils

import alektas.common.ui.models.DefinitionItem
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import kotlin.random.Random

fun generateDefinitionItem(
    definition: String = LoremIpsum(50).values.joinToString(),
    partOfSpeech: String = LoremIpsum(1).values.joinToString(),
    example: String? = LoremIpsum(30).values.joinToString(),
    imageUrl: String? = null,
    inBookmarks: Boolean = Random.nextBoolean(),
): DefinitionItem = DefinitionItem(
    definition = definition,
    partOfSpeech = partOfSpeech,
    example = example,
    imageUrl = imageUrl,
    inBookmarks = inBookmarks,
)
