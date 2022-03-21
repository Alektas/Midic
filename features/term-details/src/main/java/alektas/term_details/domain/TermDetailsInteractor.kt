package alektas.term_details.domain

import alektas.arch_base.models.Result
import alektas.common.data.local.in_memory.TermSelection
import alektas.common.domain.Bookmark
import alektas.common.domain.Definition
import alektas.common.domain.Term
import kotlinx.coroutines.flow.StateFlow

interface TermDetailsInteractor {

    fun observeTerm(): Flow<Result<TermSelection, Exception>?>

    suspend fun saveBookmark(bookmark: Bookmark)

    suspend fun deleteFromBookmarks(definition: Definition)

}
