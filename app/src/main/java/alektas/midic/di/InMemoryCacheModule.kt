package alektas.midic.di

import alektas.common.data.local.in_memory.SelectedTermCache
import alektas.common.data.local.in_memory.SelectedTermCacheImpl
import alektas.common.data.local.in_memory.SelectedTermCacheInput
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

    companion object {

        @Provides
        @Singleton
        fun provideSelectedTermCacheImpl(): SelectedTermCacheImpl = SelectedTermCacheImpl()
    }

}