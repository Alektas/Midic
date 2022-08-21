package alektas.term_search.domain

import alektas.arch_base.models.Result
import alektas.common.data.local.in_memory.term_search_events.TermSearchEvent
import alektas.common.domain.Term
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TermSearchInteractorImpl @Inject constructor(
    private val repository: TermSearchRepository
) : TermSearchInteractor {

    override fun loadTerms(query: String): Flow<Result<List<Term>, Exception>> = flow {
        emit(Result.Loading)
        emit(repository.queryTerms(query))
    }

    override fun selectTerm(term: Term) {
        repository.selectTerm(term)
    }

    override fun observeSearchEvents(): Flow<TermSearchEvent> = repository.observeSearchEvents()

}
