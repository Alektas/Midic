package alektas.term_search.domain.models

sealed interface TermSearchError {

    class NetworkError(val throwable: Throwable) : TermSearchError

}