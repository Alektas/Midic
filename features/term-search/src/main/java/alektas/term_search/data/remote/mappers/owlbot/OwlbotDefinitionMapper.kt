package alektas.term_search.data.remote.mappers.owlbot

import alektas.arch_base.mappers.Mapper
import alektas.common.domain.Definition
import alektas.common.data.remote.owlbot.dto.OwlbotDefinitionDto
import javax.inject.Inject

class OwlbotDefinitionMapper @Inject constructor() : Mapper<OwlbotDefinitionDto, Definition> {

    override fun map(input: OwlbotDefinitionDto): Definition = with(input) {
        Definition(
            id = input.hashCode().toLong(),
            partOfSpeech = type,
            definition = definition,
            example = example,
            imageUrl = imageUrl,
            inBookmarks = false
        )
    }

}
