package alektas.common.data.local.in_memory

import alektas.arch_base.models.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class SelectedTermCacheImpl @Inject constructor(): SelectedTermCache, SelectedTermCacheInput {

    private val selectedTerm = MutableStateFlow<Result<TermSelection, Exception>?>(null)

    override fun observeSelectedTerm(): Flow<Result<TermSelection, Exception>?> = selectedTerm

    override fun emit(result: Result<TermSelection, Exception>) {
        selectedTerm.value = result
    }

}