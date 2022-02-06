package alektas.term_search.di

import alektas.arch_base.mappers.Mapper
import alektas.common.domain.Definition
import alektas.common.domain.Term
import alektas.common.ui.models.DefinitionItem
import alektas.common.ui.models.TermItem
import alektas.core.di.scopes.ScreenScope
import alektas.term_search.domain.TermSearchInteractor
import alektas.term_search.domain.TermSearchInteractorImpl
import alektas.term_search.ui.TermSearchViewModel
import alektas.term_search.ui.mappers.DefinitionItemMapper
import alektas.term_search.ui.mappers.TermItemMapper
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface TermSearchModule {

    @Binds
    fun bindInteractor(impl: TermSearchInteractorImpl): TermSearchInteractor

    @Binds
    fun bindTermMapper(impl: TermItemMapper): Mapper<Term, TermItem>

    @Binds
    fun bindDefinitionMapper(impl: DefinitionItemMapper): Mapper<Definition, DefinitionItem>

    companion object {

        @ScreenScope
        @Provides
        fun provideViewModel(
            interactor: TermSearchInteractor,
            termMapper: Mapper<@JvmSuppressWildcards Term, TermItem>,
        ): TermSearchViewModel = TermSearchViewModel(
            interactor,
            termMapper,
        )
    }

}