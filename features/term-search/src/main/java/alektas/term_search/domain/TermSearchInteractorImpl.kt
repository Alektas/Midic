package alektas.term_search.domain

import alektas.arch_base.models.Result
import alektas.common.domain.Term
import alektas.term_search.domain.models.TermSearchError
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TermSearchInteractorImpl @Inject constructor(
    private val repository: TermSearchRepository
) : TermSearchInteractor {

    override fun loadTerms(query: String): Flow<Result<List<Term>, TermSearchError>> = flow {
        if (query.isBlank()) {
            emit(Result.Empty)
            return@flow
        }

        val terms = repository.queryTerms(query)
        if (terms.isEmpty()) {
            emit(Result.Empty)
        } else {
            emit(Result.Success(terms))
        }
    }

    override fun selectTerm(term: Term) {
        repository.selectTerm(term)
    }

}
