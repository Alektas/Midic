package alektas.midic

import alektas.core.utils.compose.createViewModel
import alektas.midic.theme.AppTheme
import alektas.term_search.di.DaggerTermSearchComponent
import alektas.term_search.ui.TermSearchScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    val appComponent = (application as App).appComponent
                    val viewModel = createViewModel {
                        DaggerTermSearchComponent.factory().create(appComponent).viewModel()
                    }
                    TermSearchScreen(viewModel = viewModel)
                }
            }
        }
    }

}
