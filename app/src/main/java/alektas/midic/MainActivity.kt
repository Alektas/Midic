package alektas.midic

import alektas.midic.theme.AppTheme
import alektas.midic.theme.cornersXl
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mircod.ui_components.bottom_sheet.BottomSheetScaffold

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    BottomSheetScaffold(
                        modifier = Modifier.fillMaxSize(),
                        sheetExpandedStateEnabled = true,
                        sheetShape = RoundedCornerShape(topStart = cornersXl, topEnd = cornersXl),
                        sheetBackgroundColor = MaterialTheme.colorScheme.secondaryContainer,
                        sheetElevation = 0.dp,
                        sheetContent = {
                            Text("Bottom sheet")
                        },
                    ) {
                        Text("Background")
                    }
                }
            }
        }
    }
}
