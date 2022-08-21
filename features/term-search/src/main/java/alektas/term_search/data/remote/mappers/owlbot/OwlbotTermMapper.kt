package alektas.term_search.data.remote.mappers.owlbot

import alektas.arch_base.mappers.Mapper
import alektas.common.domain.Definition
import alektas.common.domain.Term
import alektas.common.data.remote.owlbot.dto.OwlbotDefinitionDto
import alektas.common.data.remote.owlbot.dto.OwlbotTermDto
import javax.inject.Inject

class OwlbotTermMapper @Inject constructor(
    private val definitionMapper: Mapper<@JvmSuppressWildcards OwlbotDefinitionDto, Definition>
) : Mapper<OwlbotTermDto, Term> {

    override fun map(input: OwlbotTermDto): Term = with(input) {
        Term(
            word = word,
            transcription = pronunciation ?: "",
            definitions = definitions.map(definitionMapper::map)
        )
    }

}
