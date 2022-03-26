package alektas.bookmark_list.data.local.mappers

import alektas.arch_base.mappers.Mapper
import alektas.common.data.local.db.entities.DefinitionEntity
import alektas.common.domain.Definition
import javax.inject.Inject

class DefinitionEntityToDefinitionMapper @Inject constructor(): Mapper<DefinitionEntity, Definition> {

    override fun map(input: DefinitionEntity): Definition = with(input) {
        Definition(
            definition = definition,
            partOfSpeech = partOfSpeech,
            example = example,
            imageUrl = imageUrl,
            inBookmarks = true,
        )
    }

}
