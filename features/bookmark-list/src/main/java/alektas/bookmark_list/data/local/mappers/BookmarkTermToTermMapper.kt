package alektas.bookmark_list.data.local.mappers

import alektas.arch_base.mappers.Mapper
import alektas.common.data.local.db.entities.DefinitionEntity
import alektas.common.data.local.db.models.BookmarkExtended
import alektas.common.domain.Definition
import alektas.common.domain.Term
import javax.inject.Inject

class BookmarkTermToTermMapper @Inject constructor(
    private val definitionMapper: Mapper<@JvmSuppressWildcards DefinitionEntity, Definition>
) : Mapper<BookmarkExtended, Term> {

    override fun map(input: BookmarkExtended): Term = with(input) {
        Term(
            word = term.word,
            transcription = term.pronunciation,
            definitions = definitions.map { definitionMapper.map(it) }
        )
    }

}
