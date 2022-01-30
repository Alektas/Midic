package alektas.midic

import alektas.midic.theme.AppTheme
import alektas.term_search.ui.TermSearchScreen
import alektas.term_search.ui.TermSearchViewModel
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    val viewModel by viewModels<TermSearchViewModel>()
                    TermSearchScreen(viewModel = viewModel)
                }
            }
        }
    }
}
