package alektas.common.data.local

import alektas.common.domain.Term

interface SelectedTermCacheInput {

    fun emit(term: Term)

}