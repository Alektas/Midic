package alektas.term_search.ui.mappers

import alektas.arch_base.mappers.DuplexMapper
import alektas.common.domain.Definition
import alektas.common.ui.models.DefinitionItem
import javax.inject.Inject

class DefinitionItemMapper @Inject constructor() : DuplexMapper<Definition, DefinitionItem> {

    override fun mapInput(input: Definition): DefinitionItem = with(input) {
        DefinitionItem(
            id = id,
            partOfSpeech = partOfSpeech,
            definition = definition,
            inBookmarks = inBookmarks,
            example = example,
            imageUrl = imageUrl,
        )
    }

    override fun mapOutput(output: DefinitionItem): Definition = with(output) {
        Definition(
            id = id,
            partOfSpeech = partOfSpeech,
            definition = definition,
            inBookmarks = inBookmarks,
            example = example,
            imageUrl = imageUrl,
        )
    }

}
