package alektas.midic.di

import alektas.common.data.local.in_memory.SelectedTermCache
import alektas.common.data.local.in_memory.SelectedTermCacheImpl
import alektas.common.data.local.in_memory.SelectedTermCacheInput
import dagger.Binds
import dagger.Module

@Module
interface CacheModule {

    @Binds
    fun bindSelectedTermCache(impl: SelectedTermCacheImpl): SelectedTermCache

    @Binds
    fun bindSelectedTermCacheInput(impl: SelectedTermCacheImpl): SelectedTermCacheInput

}