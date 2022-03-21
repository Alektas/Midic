package alektas.term_details.domain

import alektas.arch_base.models.Result
import alektas.common.data.local.in_memory.term_selection.TermSelection
import alektas.common.domain.Bookmark
import alektas.common.domain.Definition
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TermDetailsInteractorImpl @Inject constructor(
    private val repository: TermDetailsRepository,
) : TermDetailsInteractor {

    override fun observeTerm(): Flow<Result<TermSelection, Exception>?> = repository.observeTerm()

    override suspend fun saveBookmark(bookmark: Bookmark) = repository.saveBookmark(bookmark)

    override suspend fun deleteFromBookmarks(definition: Definition) = repository.deleteFromBookmarks(definition)

}
