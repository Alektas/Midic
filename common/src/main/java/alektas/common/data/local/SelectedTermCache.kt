package alektas.common.data.local

import alektas.common.domain.Term
import kotlinx.coroutines.flow.StateFlow

interface SelectedTermCache {

    fun observeSelectedTerm(): StateFlow<Term?>

}