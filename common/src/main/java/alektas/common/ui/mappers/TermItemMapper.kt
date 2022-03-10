package alektas.common.ui.mappers

import alektas.arch_base.mappers.DuplexMapper
import alektas.common.domain.Definition
import alektas.common.domain.Term
import alektas.common.ui.models.DefinitionItem
import alektas.common.ui.models.TermItem
import javax.inject.Inject

class TermItemMapper @Inject constructor(
    private val definitionMapper: DuplexMapper<@JvmSuppressWildcards Definition, DefinitionItem>
) : DuplexMapper<Term, TermItem> {

    override fun mapInput(input: Term): TermItem = with(input) {
        TermItem(
            id = id,
            word = word,
            transcription = transcription,
            definitions = definitions.map { definitionMapper.mapInput(it) },
        )
    }

    override fun mapOutput(output: TermItem): Term = with(output) {
        Term(
            id = id,
            word = word,
            transcription = transcription,
            definitions = definitions.map { definitionMapper.mapOutput(it) }
        )
    }

}
