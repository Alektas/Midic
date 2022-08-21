package alektas.term_details.di

import alektas.core.di.scopes.ScreenScope
import alektas.term_details.ui.TermViewModel
import dagger.Component

@ScreenScope
@Component(
    modules = [TermDetailsModule::class],
    dependencies = [TermDetailsDependencies::class]
)
interface TermDetailsComponent {

    fun viewModel(): TermViewModel

    @Component.Factory
    interface Factory {
        fun create(dependencies: TermDetailsDependencies): TermDetailsComponent
    }

}