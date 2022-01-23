package alektas.term_search.ui.models

import alektas.common.ui.models.TermItem

sealed interface ScreenAction {
    class Query(val query: String) : ScreenAction
    class TermSelection(val term: TermItem) : ScreenAction
}