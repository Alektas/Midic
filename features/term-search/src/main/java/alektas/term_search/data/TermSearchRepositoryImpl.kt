package alektas.term_search.data

import alektas.arch_base.models.Result
import alektas.common.data.local.in_memory.term_search_events.TermSearchEvent
import alektas.common.data.local.in_memory.term_search_events.TermSearchEventSource
import alektas.common.domain.Term
import alektas.common.data.local.in_memory.term_selection.SelectedTermCacheInput
import alektas.common.data.local.in_memory.term_selection.TermSelection
import alektas.common.data.remote.constants.HttpErrorCode
import alektas.term_search.data.remote.RemoteTermSearchSource
import alektas.term_search.domain.TermSearchRepository
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException
import javax.inject.Inject

/**
 * Repository manage high level type of sources (in-memory cache/local persistent/remote API) and strategies
 * while which exactly source to use decide each Source by self
 * (e.g. which REST API use according to service quality state).
 */
class TermSearchRepositoryImpl @Inject constructor(
    private val remoteSource: RemoteTermSearchSource,
    private val selectedTermCache: SelectedTermCacheInput,
    private val searchEvents: TermSearchEventSource,
) : TermSearchRepository {

    override suspend fun queryTerms(query: String): Result<List<Term>, Exception> {
        if (query.isBlank()) {
            selectedTermCache.emit(Result.Success(TermSelection.Init))
            return Result.Empty
        }

        selectedTermCache.emit(Result.Loading)
        return try {
            remoteSource.queryTerms(query)
                .let { terms ->
                    if (terms.isEmpty()) {
                        selectedTermCache.emit(Result.Empty)
                        Result.Empty
                    } else {
                        selectedTermCache.emit(Result.Success(TermSelection.NotSelected))
                        Result.Success(terms)
                    }
                }
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            handleError(e)
        }
    }

    private fun handleError(e: Exception): Result<List<Term>, Exception> {
        val result = if (e is HttpException && e.code() == HttpErrorCode.NOT_FOUND) {
            Result.Empty
        } else {
            Result.Error(e)
        }
        selectedTermCache.emit(result)
        return result
    }

    override fun selectTerm(term: Term) {
        selectedTermCache.emit(Result.Success(TermSelection.Selected(term)))
    }

    override fun observeSearchEvents(): Flow<TermSearchEvent> = searchEvents.observeTermSearchEvents()

}
