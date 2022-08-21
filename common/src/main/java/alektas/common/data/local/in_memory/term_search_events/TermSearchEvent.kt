package alektas.common.data.local.in_memory.term_search_events

sealed interface TermSearchEvent {

    object Retry : TermSearchEvent

}
