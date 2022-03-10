package alektas.midic.di

import alektas.core.di.qualifiers.ApplicationContext
import alektas.term_search.di.TermSearchDependencies
import alektas.term_details.di.TermDetailsDependencies
import android.content.Context
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent : TermSearchDependencies, TermDetailsDependencies {

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance
            @ApplicationContext
            context: Context
        ): AppComponent
    }

}
