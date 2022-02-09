package alektas.term_search.domain

import alektas.common.domain.Term

interface TermSearchRepository {

    suspend fun queryTerms(query: String): List<Term>

}