package alektas.bookmark_list.di

import alektas.arch_base.mappers.Mapper
import alektas.bookmark_list.data.BookmarkListRepositoryImpl
import alektas.bookmark_list.data.local.BookmarksLocalSource
import alektas.bookmark_list.data.local.BookmarksLocalSourceImpl
import alektas.bookmark_list.data.local.mappers.BookmarkTermToTermMapper
import alektas.bookmark_list.data.local.mappers.DefinitionEntityToDefinitionMapper
import alektas.bookmark_list.domain.BookmarkListInteractor
import alektas.bookmark_list.domain.BookmarkListInteractorImpl
import alektas.bookmark_list.domain.BookmarkListRepository
import alektas.common.data.local.db.entities.DefinitionEntity
import alektas.common.data.local.db.models.BookmarkExtended
import alektas.common.domain.Definition
import alektas.common.domain.Term
import dagger.Binds
import dagger.Module

@Module
interface BookmarkListModule {

    @Binds
    fun bindRemoteSource(impl: BookmarksLocalSourceImpl): BookmarksLocalSource

    @Binds
    fun bindRepository(impl: BookmarkListRepositoryImpl): BookmarkListRepository

    @Binds
    fun bindInteractor(impl: BookmarkListInteractorImpl): BookmarkListInteractor

    @Binds
    fun bindBookmarkTermToTermMapper(impl: BookmarkTermToTermMapper): Mapper<BookmarkExtended, Term>

    @Binds
    fun bindDefinitionEntityToDefinitionMapper(impl: DefinitionEntityToDefinitionMapper): Mapper<DefinitionEntity, Definition>

}
