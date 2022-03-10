package alektas.midic.di

import dagger.Module

@Module(
    includes = [
        NetworkModule::class,
        StorageModule::class,
        InMemoryCacheModule::class,
        CommonMappersModule::class,
    ]
)
interface AppModule {

}
