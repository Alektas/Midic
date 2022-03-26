package alektas.bookmark_list.di

import alektas.bookmark_list.BookmarkListViewModel
import alektas.core.di.scopes.ScreenScope
import dagger.Component

@ScreenScope
@Component(
    modules = [BookmarkListModule::class],
    dependencies = [BookmarkListDependencies::class]
)
interface BookmarkListComponent {

    fun viewModel(): BookmarkListViewModel

    @Component.Factory
    interface Factory {
        fun create(dependencies: BookmarkListDependencies): BookmarkListComponent
    }

}
