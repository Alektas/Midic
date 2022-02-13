package alektas.term_search.data

import alektas.common.domain.Term
import alektas.term_search.data.remote.RemoteTermSearchSource
import alektas.term_search.domain.TermSearchRepository
import javax.inject.Inject

/**
 * Repository manage high level type of sources (in-memory cache/local persistent/remote API) and strategies
 * while which exactly source to use decide each Source by self
 * (e.g. which REST API use according to service quality state).
 */
class TermSearchRepositoryImpl @Inject constructor(
    private val remoteSource: RemoteTermSearchSource
) : TermSearchRepository {

    override suspend fun queryTerms(query: String): List<Term> = remoteSource.queryTerms(query)

}