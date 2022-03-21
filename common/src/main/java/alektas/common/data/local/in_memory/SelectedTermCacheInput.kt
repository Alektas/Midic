package alektas.common.data.local.in_memory

import alektas.arch_base.models.Result

interface SelectedTermCacheInput {

    fun emit(result: Result<TermSelection, Exception>)

}