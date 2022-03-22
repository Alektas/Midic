package alektas.term_details.data

import alektas.arch_base.mappers.Mapper
import alektas.arch_base.models.Result
import alektas.common.data.local.db.dao.BookmarksDao
import alektas.common.data.local.db.entities.DefinitionEntity
import alektas.common.data.local.db.entities.TermEntity
import alektas.common.data.local.db.models.BookmarkTerm
import alektas.common.data.local.in_memory.term_selection.SelectedTermCache
import alektas.common.data.local.in_memory.term_selection.TermSelection
import alektas.common.domain.Bookmark
import alektas.common.domain.Definition
import alektas.common.domain.Term
import alektas.term_details.domain.TermDetailsRepository
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class TermDetailsRepositoryImpl @Inject constructor(
    private val selectedTermCache: SelectedTermCache,
    private val bookmarksDao: BookmarksDao,
    private val bookmarkToTermEntityMapper: Mapper<@JvmSuppressWildcards Bookmark, TermEntity>,
    private val bookmarkToDefinitionEntityMapper: Mapper<@JvmSuppressWildcards Bookmark, DefinitionEntity>,
) : TermDetailsRepository {

    override fun observeTerm(): Flow<Result<TermSelection, Exception>?> {
        return selectedTermCache.observeSelectedTerm()
            .combine(bookmarksDao.observeBookmarks()) { result, bookmarks ->
                if (result !is Result.Success) return@combine result

                (result.data as? TermSelection.Selected)
                    ?.run {
                        Result.Success(copy(term.checkInBookmarks(bookmarks)))
                    }
                    ?: result
            }
    }

    private fun Term.checkInBookmarks(bookmarks: List<BookmarkTerm>): Term {
        val bookmarkDefinitions = bookmarks
            .filter { it.term.word == word }
            .flatMap { it.definitions }
        return copy(definitions = definitions.combine(bookmarkDefinitions))
    }

    private fun List<Definition>.combine(bookmarks: List<DefinitionEntity>): List<Definition> = map { model ->
        model.copy(
            inBookmarks = bookmarks.find { it.definition == model.definition } != null
        )
    }

    override suspend fun saveBookmark(bookmark: Bookmark) {
        val termEntity = bookmarkToTermEntityMapper.map(bookmark)
        val definitionEntity = bookmarkToDefinitionEntityMapper.map(bookmark)
        bookmarksDao.saveToBookmarks(termEntity, definitionEntity)
    }

    override suspend fun deleteFromBookmarks(definition: Definition) {
        bookmarksDao.deleteDefinition(definition.definition)
    }

    override fun loadRandomTerm() {
        // TODO("Not yet implemented")
    }

    override fun retryTermSearching() {
//        termSearchEvents.emit(TermSearchEvent.Retry)
    }

}
