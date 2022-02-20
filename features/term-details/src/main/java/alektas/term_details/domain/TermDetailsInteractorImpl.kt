package alektas.term_details.domain

import alektas.common.domain.Bookmark
import alektas.common.domain.Definition
import alektas.common.domain.Term
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class TermDetailsInteractorImpl @Inject constructor(
    private val repository: TermDetailsRepository,
) : TermDetailsInteractor {

    override fun observeTerm(): StateFlow<Term?> = repository.observeTerm()

    override suspend fun saveBookmark(bookmark: Bookmark) = repository.saveBookmark(bookmark)

    override suspend fun deleteFromBookmarks(definition: Definition) = repository.deleteFromBookmarks(definition)

}
