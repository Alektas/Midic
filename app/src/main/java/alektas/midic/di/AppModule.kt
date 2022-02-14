package alektas.midic.di

import dagger.Module

@Module(includes = [NetworkModule::class, CacheModule::class])
interface AppModule {

}
