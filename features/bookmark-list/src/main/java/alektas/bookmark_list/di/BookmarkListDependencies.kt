package alektas.bookmark_list.di

import alektas.arch_base.mappers.DuplexMapper
import alektas.common.data.local.db.dao.BookmarksDao
import alektas.common.domain.Term
import alektas.common.ui.models.TermItem

interface BookmarkListDependencies {

    fun bookmarkDao(): BookmarksDao

    fun termItemMapper(): DuplexMapper<Term, TermItem>

}
