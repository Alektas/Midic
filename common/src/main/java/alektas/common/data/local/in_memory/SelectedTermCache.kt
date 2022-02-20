package alektas.common.data.local.in_memory

import alektas.common.domain.Term
import kotlinx.coroutines.flow.StateFlow

interface SelectedTermCache {

    fun observeSelectedTerm(): StateFlow<Term?>

}