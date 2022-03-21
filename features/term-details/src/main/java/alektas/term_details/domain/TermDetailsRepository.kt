package alektas.term_details.domain

import alektas.arch_base.models.Result
import alektas.common.data.local.in_memory.term_selection.TermSelection
import alektas.common.domain.Bookmark
import alektas.common.domain.Definition
import kotlinx.coroutines.flow.Flow

interface TermDetailsRepository {

    fun observeTerm(): Flow<Result<TermSelection, Exception>?>

    suspend fun saveBookmark(bookmark: Bookmark)

    suspend fun deleteFromBookmarks(definition: Definition)

}
