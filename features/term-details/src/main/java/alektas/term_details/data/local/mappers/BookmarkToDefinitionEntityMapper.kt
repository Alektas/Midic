package alektas.term_details.data.local.mappers

import alektas.arch_base.mappers.Mapper
import alektas.common.data.local.db.entities.DefinitionEntity
import alektas.common.domain.Bookmark
import javax.inject.Inject

class BookmarkToDefinitionEntityMapper @Inject constructor() : Mapper<Bookmark, DefinitionEntity> {

    override fun map(input: Bookmark): DefinitionEntity = with(input) {
        DefinitionEntity(
            termWord = word,
            partOfSpeech = definition.partOfSpeech,
            example = definition.example,
            imageUrl = definition.imageUrl,
            definition = definition.definition
        )
    }

}
