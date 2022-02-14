package alektas.common.data.local

import alektas.common.domain.Term
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class SelectedTermCacheImpl @Inject constructor(): SelectedTermCache, SelectedTermCacheInput {

    private val selectedTerm = MutableStateFlow<Term?>(null)

    override fun observeSelectedTerm(): StateFlow<Term?> = selectedTerm

    override fun emit(term: Term) {
        selectedTerm.value = term
    }

}