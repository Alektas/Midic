package alektas.midic.di

import alektas.core.di.qualifiers.ApplicationContext
import android.content.Context
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

    @ApplicationContext
    fun appContext(): Context

}
