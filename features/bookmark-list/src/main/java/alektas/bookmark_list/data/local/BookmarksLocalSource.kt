package alektas.bookmark_list.data.local

import alektas.common.domain.Term
import kotlinx.coroutines.flow.Flow

interface BookmarksLocalSource {

    fun observeBookmarks(): Flow<List<Term>>

}
