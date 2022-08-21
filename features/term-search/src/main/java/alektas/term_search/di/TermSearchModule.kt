package alektas.term_search.di

import alektas.arch_base.mappers.Mapper
import alektas.common.data.remote.owlbot.dto.OwlbotDefinitionDto
import alektas.common.data.remote.owlbot.dto.OwlbotTermDto
import alektas.common.domain.Definition
import alektas.common.domain.Term
import alektas.term_search.data.TermSearchRepositoryImpl
import alektas.term_search.data.remote.RemoteTermSearchSource
import alektas.term_search.data.remote.RemoteTermSearchSourceImpl
import alektas.term_search.data.remote.mappers.owlbot.OwlbotDefinitionMapper
import alektas.term_search.data.remote.mappers.owlbot.OwlbotTermMapper
import alektas.term_search.domain.TermSearchInteractor
import alektas.term_search.domain.TermSearchInteractorImpl
import alektas.term_search.domain.TermSearchRepository
import dagger.Binds
import dagger.Module

@Module
interface TermSearchModule {

    @Binds
    fun bindRemoteSource(impl: RemoteTermSearchSourceImpl): RemoteTermSearchSource

    @Binds
    fun bindRepository(impl: TermSearchRepositoryImpl): TermSearchRepository

    @Binds
    fun bindInteractor(impl: TermSearchInteractorImpl): TermSearchInteractor

    @Binds
    fun bindTermDtoMapper(impl: OwlbotTermMapper): Mapper<OwlbotTermDto, Term>

    @Binds
    fun bindDefinitionDtoMapper(impl: OwlbotDefinitionMapper): Mapper<OwlbotDefinitionDto, Definition>

}