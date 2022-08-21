package alektas.term_search.data.remote

import alektas.common.domain.Term

interface RemoteTermSearchSource {

    suspend fun queryTerms(query: String): List<Term>

}