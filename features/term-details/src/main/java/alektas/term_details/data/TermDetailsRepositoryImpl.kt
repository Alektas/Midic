package alektas.term_details.data

import alektas.common.data.local.in_memory.SelectedTermCache
import alektas.common.domain.Term
import alektas.term_details.domain.TermDetailsRepository
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class TermDetailsRepositoryImpl @Inject constructor(
    private val selectedTermCache: SelectedTermCache,
): TermDetailsRepository {

    override fun observeTerm(): StateFlow<Term?> {
        return selectedTermCache.observeSelectedTerm()
    }

    override fun saveToBookmarks(term: Term) {
        TODO("Not yet implemented")
    }

}