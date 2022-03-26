package alektas.bookmark_list

import alektas.arch_base.mappers.DuplexMapper
import alektas.bookmark_list.domain.BookmarkListInteractor
import alektas.bookmark_list.ui.models.Action
import alektas.bookmark_list.ui.models.Event
import alektas.bookmark_list.ui.models.UiState
import alektas.common.domain.Term
import alektas.common.ui.models.TermItem
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class BookmarkListViewModel @Inject constructor(
    private val interactor: BookmarkListInteractor,
    private val termMapper: DuplexMapper<Term, TermItem>,
) : ViewModel() {

    private val _screenState = MutableStateFlow<UiState>(UiState.Placeholder)
    val screenState: StateFlow<UiState> = _screenState

    private val _screenEvents = Channel<Event>(capacity = Channel.UNLIMITED)
    val screenEvents: Flow<Event> = _screenEvents.receiveAsFlow()

    init {
        observeBookmarks()
    }

    private fun observeBookmarks() {
        interactor.observeBookmarks()
            .onStart {
                _screenState.value = UiState.Loading
            }
            .map { terms ->
                terms.map { termMapper.mapInput(it) }
            }
            .onEach { terms ->
                _screenState.value = UiState.Bookmarks(terms)
            }
            .launchIn(viewModelScope)
    }

    fun onAction(action: Action) {
        when (action) {
            is Action.TermSelection -> {
                // TODO()
            }
        }
    }

    private fun emitEvent(event: Event) {
        _screenEvents.trySend(event)
    }

}
