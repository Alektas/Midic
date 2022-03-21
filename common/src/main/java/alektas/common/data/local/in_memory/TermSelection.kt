package alektas.common.data.local.in_memory

import alektas.common.domain.Term

sealed interface TermSelection {
    object Init : TermSelection
    object NotSelected : TermSelection
    data class Selected(val term: Term) : TermSelection
}
