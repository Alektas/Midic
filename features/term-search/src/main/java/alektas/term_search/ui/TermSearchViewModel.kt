package alektas.term_search.ui

import alektas.arch_base.mappers.DuplexMapper
import alektas.arch_base.models.Result
import alektas.common.data.local.in_memory.term_search_events.TermSearchEvent
import alektas.common.domain.Term
import alektas.common.ui.models.TermItem
import alektas.term_search.domain.TermSearchInteractor
import alektas.term_search.ui.models.ScreenAction
import alektas.term_search.ui.models.ScreenEvent
import alektas.term_search.ui.models.ScreenState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import kotlin.time.Duration.Companion.milliseconds

class TermSearchViewModel @Inject constructor(
    private val interactor: TermSearchInteractor,
    private val termMapper: DuplexMapper<Term, TermItem>,
) : ViewModel() {

    private val _query = MutableStateFlow("")

    private val _screenState = MutableStateFlow<ScreenState>(ScreenState.NoResults(""))
    val screenState: StateFlow<ScreenState> = _screenState

    private val _screenEvents = Channel<ScreenEvent>(capacity = Channel.UNLIMITED)
    val screenEvents: Flow<ScreenEvent> = _screenEvents.receiveAsFlow()

    init {
        handleInputUpdate()
        handleSearching()
        observeSearchEvents()
    }

    private fun handleInputUpdate() {
        _query
            .onEach { query ->
                _screenState.update { it.withQuery(query) }
            }
            .launchIn(viewModelScope)
    }

    private fun handleSearching() {
        _query
            .debounce(300.milliseconds)
            .flatMapLatest { query ->
                handleQuery(query)
            }
            .onEach { state ->
                _screenState.value = state
            }
            .launchIn(viewModelScope)
    }

    private suspend fun handleQuery(query: String): Flow<ScreenState> = interactor.loadTerms(query)
        .map { result ->
            val newestQuery = screenState.value.searchQuery // can be newer than 'query' because of debouncing
            when (result) {
                is Result.Loading -> {
                    ScreenState.Loading(newestQuery)
                }
                is Result.Empty -> {
                    ScreenState.NoResults(newestQuery)
                }
                is Result.Error -> {
                    emitError(result.data)
                    ScreenState.NoResults(newestQuery)
                }
                is Result.Success -> {
                    val termItems = result.data.map { termMapper.mapInput(it) }
                    ScreenState.Results(newestQuery, termItems)
                }
            }
        }
        .catch { error ->
            emitError(error)
            emit(ScreenState.NoResults(query))
        }

    private fun observeSearchEvents() {
        interactor.observeSearchEvents()
            .onEach { event ->
                when (event) {
                    TermSearchEvent.Retry -> {
                        onAction(ScreenAction.Query(screenState.value.searchQuery))
                    }
                }
            }
            .launchIn(viewModelScope)
    }

    fun onAction(action: ScreenAction) {
        when (action) {
            is ScreenAction.Query -> _query.tryEmit(action.query)
            is ScreenAction.TermSelection -> handleTermSelection(action.term)
        }
    }

    private fun handleTermSelection(termItem: TermItem) {
        emitEvent(ScreenEvent.CollapseSearchResults)
        interactor.selectTerm(termMapper.mapOutput(termItem))
    }

    private fun emitError(error: Throwable) {
        emitEvent(ScreenEvent.Error(error.localizedMessage ?: error.toString()))
    }

    private fun emitEvent(event: ScreenEvent) {
        _screenEvents.trySend(event)
    }

}
