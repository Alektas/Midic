package alektas.term_search.data

import alektas.arch_base.models.Result
import alektas.common.domain.Term
import alektas.common.data.local.in_memory.SelectedTermCacheInput
import alektas.common.data.local.in_memory.TermSelection
import alektas.term_search.data.remote.RemoteTermSearchSource
import alektas.term_search.domain.TermSearchRepository
import javax.inject.Inject

/**
 * Repository manage high level type of sources (in-memory cache/local persistent/remote API) and strategies
 * while which exactly source to use decide each Source by self
 * (e.g. which REST API use according to service quality state).
 */
class TermSearchRepositoryImpl @Inject constructor(
    private val remoteSource: RemoteTermSearchSource,
    private val inMemoryCache: SelectedTermCacheInput,
) : TermSearchRepository {

    override suspend fun queryTerms(query: String): List<Term> = with(inMemoryCache) {
        if (query.isBlank()) {
            emit(Result.Success(TermSelection.Init))
            return@with emptyList()
        }

        emit(Result.Loading)
        return try {
            remoteSource.queryTerms(query)
                .also { terms ->
                    if (terms.isEmpty()) {
                        emit(Result.Empty)
                    } else {
                        emit(Result.Success(TermSelection.NotSelected))
                    }
                }
        } catch (e: Exception) {
            emit(Result.Error(e))
            throw e
        }
    }

    override fun selectTerm(term: Term) {
        inMemoryCache.emit(Result.Success(TermSelection.Selected(term)))
    }

}
