package alektas.term_search.domain

import alektas.arch_base.models.Result
import alektas.common.data.local.in_memory.term_search_events.TermSearchEvent
import alektas.common.domain.Term
import alektas.term_search.domain.models.TermSearchError
import kotlinx.coroutines.flow.Flow

interface TermSearchInteractor {

    fun loadTerms(query: String): Flow<Result<List<Term>, TermSearchError>>

    fun selectTerm(term: Term)

    fun observeSearchEvents(): Flow<TermSearchEvent>

}