package alektas.term_search.data.remote

import alektas.arch_base.mappers.Mapper
import alektas.common.domain.Term
import alektas.common.data.remote.owlbot.OwlbotApi
import alektas.common.data.remote.owlbot.dto.OwlbotTermDto
import javax.inject.Inject

class RemoteTermSearchSourceImpl @Inject constructor(
    private val owlbotApi: OwlbotApi,
    private val owlbotMapper: Mapper<@JvmSuppressWildcards OwlbotTermDto, Term>,
): RemoteTermSearchSource {

    override suspend fun queryTerms(query: String): List<Term> {
        return listOf(
            owlbotMapper.map(owlbotApi.queryTerms(query))
        )
    }

}