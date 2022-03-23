package alektas.bookmark_list.ui.models

import alektas.common.ui.models.TermItem

sealed interface Action {

    class TermSelection(val term: TermItem) : Action

}
