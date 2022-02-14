package alektas.midic.di

import alektas.common.data.local.SelectedTermCache
import alektas.common.data.local.SelectedTermCacheImpl
import alektas.common.data.local.SelectedTermCacheInput
import dagger.Binds
import dagger.Module

@Module
interface CacheModule {

    @Binds
    fun bindSelectedTermCache(impl: SelectedTermCacheImpl): SelectedTermCache

    @Binds
    fun bindSelectedTermCacheInput(impl: SelectedTermCacheImpl): SelectedTermCacheInput

}