package alektas.midic

import alektas.core.utils.compose.createViewModel
import alektas.midic.theme.AppTheme
import alektas.midic.theme.paddingX2
import alektas.term_details.di.DaggerTermDetailsComponent
import alektas.term_details.ui.SelectedTermScreen
import alektas.term_search.di.DaggerTermSearchComponent
import alektas.term_search.ui.TermSearchScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    val appComponent = (application as App).appComponent
                    val searchViewModel = createViewModel {
                        DaggerTermSearchComponent.factory().create(appComponent).viewModel()
                    }
                    val resultViewModel = createViewModel {
                        DaggerTermDetailsComponent.factory().create(appComponent).viewModel()
                    }
                    TermSearchScreen(
                        viewModel = searchViewModel,
                        backgroundContent = { padding ->
                            SelectedTermScreen(
                                viewModel = resultViewModel,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(
                                        bottom = padding.calculateBottomPadding() + paddingX2,
                                        top = paddingX2,
                                    )
                            )
                        },
                    )
                }
            }
        }
    }

}
