package alektas.midic.di

import alektas.term_search.di.TermSearchDependencies
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent : TermSearchDependencies {

    @Component.Factory
    interface Factory {
        fun create(): AppComponent
    }

}
