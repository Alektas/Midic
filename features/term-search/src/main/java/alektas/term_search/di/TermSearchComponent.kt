package alektas.term_search.di

import alektas.core.di.scopes.ScreenScope
import alektas.term_search.ui.TermSearchViewModel
import dagger.Component

@ScreenScope
@Component(
    modules = [TermSearchModule::class],
    dependencies = [TermSearchDependencies::class]
)
interface TermSearchComponent {

    fun viewModel(): TermSearchViewModel

    @Component.Factory
    interface Factory {
        fun create(dependencies: TermSearchDependencies): TermSearchComponent
    }

}