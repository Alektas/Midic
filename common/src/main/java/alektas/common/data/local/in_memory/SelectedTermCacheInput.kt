package alektas.common.data.local.in_memory

import alektas.common.domain.Term

interface SelectedTermCacheInput {

    fun emit(term: Term)

}