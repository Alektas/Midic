package alektas.term_search.ui.mappers

import alektas.arch_base.mappers.Mapper
import alektas.common.domain.Definition
import alektas.common.ui.models.DefinitionItem
import javax.inject.Inject

class DefinitionItemMapper @Inject constructor() : Mapper<Definition, DefinitionItem> {

    override fun map(input: Definition): DefinitionItem = with(input) {
        DefinitionItem(
            id = id,
            partOfSpeech = partOfSpeech,
            definition = definition,
            example = example,
            imageUrl = imageUrl,
        )
    }

}
