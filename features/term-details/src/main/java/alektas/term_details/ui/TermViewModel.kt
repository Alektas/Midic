package alektas.term_details.ui

import alektas.arch_base.mappers.DuplexMapper
import alektas.arch_base.models.Result
import alektas.arch_base.ui.BaseViewModel
import alektas.common.data.local.in_memory.term_selection.TermSelection
import alektas.common.domain.Bookmark
import alektas.common.domain.Definition
import alektas.common.domain.Term
import alektas.common.ui.models.DefinitionItem
import alektas.common.ui.models.TermItem
import alektas.term_details.domain.TermDetailsInteractor
import alektas.term_details.ui.models.Action
import alektas.term_details.ui.models.UiState
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class TermViewModel @Inject constructor(
    private val interactor: TermDetailsInteractor,
    private val termMapper: DuplexMapper<Term, TermItem>,
    private val definitionMapper: DuplexMapper<Definition, DefinitionItem>,
) : BaseViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Init)
    val uiState: StateFlow<UiState> = _uiState

    init {
        interactor.observeTerm()
            .onEach { result ->
                _uiState.value = when (result) {
                    null -> UiState.Init
                    is Result.Loading -> UiState.Searching
                    is Result.Empty -> UiState.NothingFound
                    is Result.Error -> UiState.Error(result.data)
                    is Result.Success -> when (result.data) {
                        TermSelection.Init -> UiState.Init
                        TermSelection.NotSelected -> UiState.TermNotSelected
                        is TermSelection.Selected -> {
                            val term = (result.data as TermSelection.Selected).term
                            UiState.Term(termMapper.mapInput(term))
                        }
                    }
                }
            }
            .launchIn(viewModelScope)
    }

    fun onAction(action: Action) {
        when (action) {
            is Action.GetRandomTerm -> interactor.loadRandomTerm()
            is Action.Retry -> interactor.retryTermSearching()
            is Action.Bookmark -> updateBookmark(action.definition)
            is Action.Copy -> {
                // TODO()
            }
            is Action.Share -> {
                // TODO()
            }
        }
    }

    private fun updateBookmark(definitionItem: DefinitionItem) {
        val currentState = uiState.value as? UiState.Term ?: return
        val definition = definitionMapper.mapOutput(definitionItem)

        if (definition.inBookmarks) {
            deleteTermFromBookmark(definition)
        } else {
            saveTermToBookmark(currentState.term, definition)
        }
    }

    private fun saveTermToBookmark(term: TermItem, definition: Definition) {
        launchSafely {
            val bookmark = Bookmark(
                word = term.word,
                transcription = term.transcription,
                definition = definition
            )
            interactor.saveBookmark(bookmark)
        }
    }

    private fun deleteTermFromBookmark(definition: Definition) {
        launchSafely {
            interactor.deleteFromBookmarks(definition)
        }
    }

}
