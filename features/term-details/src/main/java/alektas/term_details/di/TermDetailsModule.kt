package alektas.term_details.di

import alektas.arch_base.mappers.DuplexMapper
import alektas.arch_base.mappers.Mapper
import alektas.common.data.local.db.entities.DefinitionEntity
import alektas.common.data.local.db.entities.TermEntity
import alektas.common.domain.Bookmark
import alektas.common.domain.Definition
import alektas.common.domain.Term
import alektas.common.ui.models.DefinitionItem
import alektas.common.ui.models.TermItem
import alektas.core.di.scopes.ScreenScope
import alektas.term_details.data.TermDetailsRepositoryImpl
import alektas.term_details.data.local.mappers.BookmarkToDefinitionEntityMapper
import alektas.term_details.data.local.mappers.BookmarkToTermEntityMapper
import alektas.term_details.domain.TermDetailsInteractor
import alektas.term_details.domain.TermDetailsInteractorImpl
import alektas.term_details.domain.TermDetailsRepository
//import alektas.term_details.ui.TermViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides

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

    companion object {

//        @ScreenScope
//        @Provides
//        fun provideViewModel(
//            interactor: TermDetailsInteractor,
//            termMapper: DuplexMapper<@JvmSuppressWildcards Term, TermItem>,
//            definitionMapper: DuplexMapper<@JvmSuppressWildcards Definition, DefinitionItem>,
//        ): TermViewModel = TermViewModel(
//            interactor,
//            termMapper,
//            definitionMapper,
//        )
    }

}