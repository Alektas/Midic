package alektas.term_search.ui

import alektas.common.ui.models.DefinitionItem
import alektas.common.ui.models.TermItem
import alektas.term_search.ui.models.ScreenAction
import alektas.term_search.ui.models.ScreenEvent
import alektas.term_search.ui.models.ScreenState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.time.Duration.Companion.milliseconds

class TermSearchViewModel @Inject constructor(
// TODO
) : ViewModel() {

    private val _query = MutableSharedFlow<String>(extraBufferCapacity = Int.MAX_VALUE)

    private val _screenState = MutableStateFlow<ScreenState>(ScreenState.NoResults(""))
    val screenState: StateFlow<ScreenState> = _screenState

    private val _screenEvents = Channel<ScreenEvent>(capacity = Channel.UNLIMITED)
    val screenEvents: Flow<ScreenEvent> = _screenEvents.receiveAsFlow()

    init {
        handleInputUpdate()
        handleSearching()
    }

    private fun handleInputUpdate() {
        _query
            .onEach { query ->
                _screenState.value = screenState.value.withQuery(query)
            }
            .launchIn(viewModelScope)
    }

    private fun handleSearching() {
        _query
            .debounce(300.milliseconds)
            .flatMapLatest { query ->
                handleQuery(query)
            }
            .onStart {
                emit(ScreenState.NoResults(""))
            }
            .catch { error ->
                emit(ScreenState.NoResults(""))
                emitEvent(ScreenEvent.Error(error.localizedMessage ?: error.toString()))
            }
            .onEach { state ->
                _screenState.value = state
            }
            .launchIn(viewModelScope)
    }

    private suspend fun handleQuery(query: String): Flow<ScreenState> = flow {
        if (query.isBlank()) {
            emit(ScreenState.NoResults(query))
            return@flow
        }

        emit(ScreenState.Loading(query))
        val terms = queryTerms(query)
        if (terms.isEmpty()) {
            emit(ScreenState.NoResults(query))
        } else {
            emit(ScreenState.Results(query, terms))
        }
    }

    private suspend fun queryTerms(query: String): List<TermItem> = buildList {
        delay(1_000)
        repeat(50) {
            val item = TermItem(
                id = it.toLong(),
                word = "$query $it",
                transcription = "/$query $it/",
                definitions = buildList {
                    repeat(it) {
                        add(DefinitionItem(it.toLong(), "noun", "Definition"))
                    }
                }
            )
            add(item)
        }
    }

    fun onAction(action: ScreenAction) {
        when (action) {
            is ScreenAction.Query -> _query.tryEmit(action.query)
            is ScreenAction.TermSelection -> handleTermSelection(action.term)
        }
    }

    private fun handleTermSelection(termItem: TermItem) {
        emitEvent(ScreenEvent.CollapseSearchResults)
        // TODO: provide selected term to repository
    }

    private fun emitEvent(event: ScreenEvent) {
        _screenEvents.trySend(event)
    }

}
