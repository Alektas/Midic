package alektas.bookmark_list.data

import alektas.bookmark_list.data.local.BookmarksLocalSource
import alektas.bookmark_list.domain.BookmarkListRepository
import alektas.common.domain.Term
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BookmarkListRepositoryImpl @Inject constructor(
    private val localSource: BookmarksLocalSource,
) : BookmarkListRepository {

    override fun observeBookmarks(): Flow<List<Term>> = localSource.observeBookmarks()

}
