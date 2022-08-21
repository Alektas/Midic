package alektas.bookmark_list.data.local

import alektas.arch_base.mappers.Mapper
import alektas.common.data.local.db.dao.BookmarksDao
import alektas.common.data.local.db.models.BookmarkExtended
import alektas.common.domain.Term
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class BookmarksLocalSourceImpl @Inject constructor(
    private val bookmarksDao: BookmarksDao,
    private val termMapper: Mapper<@JvmSuppressWildcards BookmarkExtended, Term>
): BookmarksLocalSource {

    override fun observeBookmarks(): Flow<List<Term>> = bookmarksDao.observeBookmarks()
        .map { bookmarks ->
            bookmarks.map { termMapper.map(it) }
        }

}
