package alektas.common.data.local.in_memory.term_selection

import alektas.arch_base.models.Result
import kotlinx.coroutines.flow.Flow

interface SelectedTermCache {

    fun observeSelectedTerm(): Flow<Result<TermSelection, Exception>?>

}