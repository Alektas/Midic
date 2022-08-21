package alektas.term_details.ui.models

import alektas.common.ui.models.DefinitionItem

sealed interface Action {
    data class Share(val definition: DefinitionItem) : Action
    data class Copy(val definition: DefinitionItem) : Action
    data class Bookmark(val definition: DefinitionItem) : Action
    object Retry : Action
    object GetRandomTerm : Action
}
