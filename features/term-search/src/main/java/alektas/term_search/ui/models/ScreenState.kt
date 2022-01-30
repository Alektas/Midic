package alektas.term_search.ui.models

import alektas.common.ui.models.TermItem

sealed class ScreenState(val searchQuery: String) {

    data class Loading(@Transient val query: String) : ScreenState(query)

    data class NoResults(@Transient val query: String) : ScreenState(query)

    data class Results(@Transient val query: String, val terms: List<TermItem>) : ScreenState(query)

    fun withQuery(newQuery: String): ScreenState {
        return when (this) {
            is Loading -> copy(query = newQuery)
            is NoResults -> copy(query = newQuery)
            is Results -> copy(query = newQuery)
        }
    }

}
