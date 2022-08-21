package alektas.bookmark_list.ui.models

import alektas.common.ui.models.TermItem

sealed interface Event {

    data class ShowDefinitions(val term: TermItem) : Event

}
