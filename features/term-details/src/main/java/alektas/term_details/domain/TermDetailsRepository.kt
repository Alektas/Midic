package alektas.term_details.domain

import alektas.common.domain.Bookmark
import alektas.common.domain.Definition
import alektas.common.domain.Term
import kotlinx.coroutines.flow.StateFlow

interface TermDetailsRepository {

    fun observeTerm(): StateFlow<Term?>

    suspend fun saveBookmark(bookmark: Bookmark)

    suspend fun deleteFromBookmarks(definition: Definition)

}
