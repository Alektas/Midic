package alektas.midic.ui.features

import alektas.bookmark_list.di.DaggerBookmarkListComponent
import alektas.bookmark_list.ui.BookmarkListScreen
import alektas.core.utils.compose.createViewModel
import alektas.midic.di.AppComponent
import androidx.compose.runtime.Composable

@Composable
fun BookmarkListFeature(appComponent: AppComponent, onBackButtonClick: () -> Unit) {
    val bookmarkListViewModel = createViewModel {
        DaggerBookmarkListComponent.factory().create(appComponent).viewModel()
    }
    BookmarkListScreen(
        viewModel = bookmarkListViewModel,
        onBackButtonClick = onBackButtonClick
    )
}
