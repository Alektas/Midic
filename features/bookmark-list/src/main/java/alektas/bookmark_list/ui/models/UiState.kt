package alektas.bookmark_list.ui.models

import alektas.common.ui.models.TermItem

sealed interface UiState {

    object Placeholder : UiState

    object Loading : UiState

    data class Bookmarks(val bookmarks: List<TermItem>): UiState

}
