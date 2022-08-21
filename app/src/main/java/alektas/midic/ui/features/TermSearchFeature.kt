package alektas.midic.ui.features

import alektas.core.utils.compose.createViewModel
import alektas.midic.di.AppComponent
import alektas.midic.theme.paddingX2
import alektas.term_search.di.DaggerTermSearchComponent
import alektas.term_search.ui.TermSearchScreen
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun TermSearchFeature(appComponent: AppComponent, onBookmarksClick: () -> Unit) {
    val searchViewModel = createViewModel {
        DaggerTermSearchComponent.factory().create(appComponent).viewModel()
    }
    TermSearchScreen(
        viewModel = searchViewModel,
        onBookmarksClick = onBookmarksClick,
        backgroundContent = { padding ->
            SelectedTermFeature(
                appComponent = appComponent,
                modifier = Modifier
                    .padding(
                        bottom = padding.calculateBottomPadding() + paddingX2,
                        top = paddingX2,
                    )
            )
        },
    )
}
