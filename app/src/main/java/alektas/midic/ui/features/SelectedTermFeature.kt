package alektas.midic.ui.features

import alektas.core.utils.compose.createViewModel
import alektas.midic.di.AppComponent
import alektas.term_details.di.DaggerTermDetailsComponent
import alektas.term_details.ui.SelectedTermScreen
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SelectedTermFeature(appComponent: AppComponent, modifier: Modifier = Modifier) {
    val resultViewModel = createViewModel {
        DaggerTermDetailsComponent.factory().create(appComponent).viewModel()
    }
    SelectedTermScreen(
        viewModel = resultViewModel,
        modifier = modifier.fillMaxSize()
    )
}
