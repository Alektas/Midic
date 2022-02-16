package alektas.term_details.data

import alektas.arch_base.mappers.Mapper
import alektas.common.data.local.db.dao.BookmarksDao
import alektas.common.data.local.db.entities.DefinitionEntity
import alektas.common.data.local.db.entities.TermEntity
import alektas.common.data.local.in_memory.SelectedTermCache
import alektas.common.domain.Bookmark
import alektas.common.domain.Definition
import alektas.common.domain.Term
import alektas.term_details.domain.TermDetailsRepository
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class TermDetailsRepositoryImpl @Inject constructor(
    private val selectedTermCache: SelectedTermCache,
    private val bookmarksDao: BookmarksDao,
    private val bookmarkToTermEntityMapper: Mapper<@JvmSuppressWildcards Bookmark, TermEntity>,
    private val bookmarkToDefinitionEntityMapper: Mapper<@JvmSuppressWildcards Bookmark, DefinitionEntity>,
) : TermDetailsRepository {

    override fun observeTerm(): StateFlow<Term?> {
        return selectedTermCache.observeSelectedTerm()
    }

    override suspend fun saveBookmark(bookmark: Bookmark) {
        val termEntity = bookmarkToTermEntityMapper.map(bookmark)
        val definitionEntity = bookmarkToDefinitionEntityMapper.map(bookmark)
        bookmarksDao.saveToBookmarks(termEntity, definitionEntity)
    }

    override suspend fun deleteFromBookmarks(definition: Definition) {
        bookmarksDao.deleteDefinition(definition.id)
    }

}
