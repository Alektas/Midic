package alektas.common.data.local.in_memory.term_selection

import alektas.common.domain.Term

sealed interface TermSelection {
    object Init : TermSelection
    object NotSelected : TermSelection
    data class Selected(val term: Term) : TermSelection
}
