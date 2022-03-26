package alektas.bookmark_list.domain

import alektas.common.domain.Term
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BookmarkListInteractorImpl @Inject constructor(
    private val repository: BookmarkListRepository
) : BookmarkListInteractor {

    override fun observeBookmarks(): Flow<List<Term>> = repository.observeBookmarks()

}
