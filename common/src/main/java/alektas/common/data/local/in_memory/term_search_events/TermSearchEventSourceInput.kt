package alektas.common.data.local.in_memory.term_search_events

interface TermSearchEventSourceInput {

    fun emit(event: TermSearchEvent)

}
