package alektas.term_details.ui.models

import alektas.common.ui.models.TermItem

sealed interface UiState {

    object Init : UiState

    object Searching : UiState

    object NothingFound : UiState

    data class Error(val error: Exception) : UiState

    object TermNotSelected : UiState

    data class Term(val term: TermItem): UiState

}
