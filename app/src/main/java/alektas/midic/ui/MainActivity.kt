package alektas.midic.ui

import alektas.midic.App
import alektas.midic.theme.AppTheme
import alektas.midic.ui.navigation.AppNavigation
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.SideEffect
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                val systemUiController = rememberSystemUiController()
                val systemUiColor = MaterialTheme.colorScheme.surface
                SideEffect {
                    systemUiController.setSystemBarsColor(systemUiColor)
                }

                Surface(color = MaterialTheme.colorScheme.background) {
                    AppNavigation((application as App).appComponent)
                }
            }
        }
    }

}
