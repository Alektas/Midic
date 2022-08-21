package alektas.midic.di

import alektas.common.data.local.in_memory.term_search_events.TermSearchEventSource
import alektas.common.data.local.in_memory.term_search_events.TermSearchEventSourceImpl
import alektas.common.data.local.in_memory.term_search_events.TermSearchEventSourceInput
import alektas.common.data.local.in_memory.term_selection.SelectedTermCache
import alektas.common.data.local.in_memory.term_selection.SelectedTermCacheImpl
import alektas.common.data.local.in_memory.term_selection.SelectedTermCacheInput
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
interface InMemoryCacheModule {

    @Binds
    fun bindSelectedTermCache(impl: SelectedTermCacheImpl): SelectedTermCache

    @Binds
    fun bindSelectedTermCacheInput(impl: SelectedTermCacheImpl): SelectedTermCacheInput

    @Binds
    fun bindTermSearchEventSource(impl: TermSearchEventSourceImpl): TermSearchEventSource

    @Binds
    fun bindTermSearchEventSourceInput(impl: TermSearchEventSourceImpl): TermSearchEventSourceInput

    companion object {

        @Provides
        @Singleton
        fun provideSelectedTermCacheImpl(): SelectedTermCacheImpl = SelectedTermCacheImpl()

        @Provides
        @Singleton
        fun provideTermSearchEventSourceImpl(): TermSearchEventSourceImpl = TermSearchEventSourceImpl()
    }

}
