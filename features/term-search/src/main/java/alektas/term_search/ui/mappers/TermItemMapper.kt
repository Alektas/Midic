package alektas.term_search.ui.mappers

import alektas.arch_base.mappers.Mapper
import alektas.common.domain.Definition
import alektas.common.domain.Term
import alektas.common.ui.models.DefinitionItem
import alektas.common.ui.models.TermItem
import javax.inject.Inject

class TermItemMapper @Inject constructor(
    private val definitionMapper: Mapper<@JvmSuppressWildcards Definition, DefinitionItem>
) : Mapper<Term, TermItem> {

    override fun map(input: Term): TermItem = with(input) {
        TermItem(
            id = id,
            word = word,
            transcription = transcription,
            definitions = definitions.map { definitionMapper.map(it) },
        )
    }

}
