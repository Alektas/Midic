package alektas.common.data.local.in_memory.term_search_events

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

class TermSearchEventSourceImpl @Inject constructor(): TermSearchEventSource, TermSearchEventSourceInput {

    private val events = Channel<TermSearchEvent>(Channel.UNLIMITED)

    override fun observeTermSearchEvents(): Flow<TermSearchEvent> = events.receiveAsFlow()

    override fun emit(event: TermSearchEvent) {
        events.trySendBlocking(event)
    }

}
