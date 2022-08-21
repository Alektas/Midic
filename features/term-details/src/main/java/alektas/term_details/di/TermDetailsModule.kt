package alektas.term_details.di

import alektas.arch_base.mappers.Mapper
import alektas.common.data.local.db.entities.DefinitionEntity
import alektas.common.data.local.db.entities.TermEntity
import alektas.common.domain.Bookmark
import alektas.term_details.data.TermDetailsRepositoryImpl
import alektas.term_details.data.local.mappers.BookmarkToDefinitionEntityMapper
import alektas.term_details.data.local.mappers.BookmarkToTermEntityMapper
import alektas.term_details.domain.TermDetailsInteractor
import alektas.term_details.domain.TermDetailsInteractorImpl
import alektas.term_details.domain.TermDetailsRepository
import dagger.Binds
import dagger.Module

@Module
interface TermDetailsModule {

    @Binds
    fun bindRepository(impl: TermDetailsRepositoryImpl): TermDetailsRepository

    @Binds
    fun bindInteractor(impl: TermDetailsInteractorImpl): TermDetailsInteractor

    @Binds
    fun bindBookmarkToTermEntityMapper(impl: BookmarkToTermEntityMapper): Mapper<Bookmark, TermEntity>

    @Binds
    fun bindBookmarkToDefinitionEntityMapper(impl: BookmarkToDefinitionEntityMapper): Mapper<Bookmark, DefinitionEntity>

}