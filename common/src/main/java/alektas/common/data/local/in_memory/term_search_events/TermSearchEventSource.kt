package alektas.common.data.local.in_memory.term_search_events

import kotlinx.coroutines.flow.Flow

interface TermSearchEventSource {

    fun observeTermSearchEvents(): Flow<TermSearchEvent>

}
