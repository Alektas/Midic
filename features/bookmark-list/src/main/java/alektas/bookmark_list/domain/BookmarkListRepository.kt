package alektas.bookmark_list.domain

import alektas.common.domain.Term
import kotlinx.coroutines.flow.Flow

interface BookmarkListRepository {

    fun observeBookmarks(): Flow<List<Term>>

}
