package alektas.term_search.domain

import alektas.arch_base.models.Result
import alektas.common.domain.Definition
import alektas.common.domain.Term
import alektas.term_search.domain.models.TermSearchError
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TermSearchInteractorImpl @Inject constructor(
    // TODO
) : TermSearchInteractor {

    override fun loadTerms(query: String): Flow<Result<List<Term>, TermSearchError>> = flow {
        if (query.isBlank()) {
            emit(Result.Empty)
            return@flow
        }

        val terms = queryTerms(query)
        if (terms.isEmpty()) {
            emit(Result.Empty)
        } else {
            emit(Result.Success(terms))
        }
    }

    private suspend fun queryTerms(query: String): List<Term> = buildList {
        delay(1_000)
        repeat(50) {
            val item = Term(
                id = it.toLong(),
                word = "$query $it",
                transcription = "/$query $it/",
                definitions = buildList {
                    repeat(it) {
                        add(Definition(it.toLong(), "noun", "Definition"))
                    }
                }
            )
            add(item)
        }
    }

}