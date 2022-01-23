package alektas.midic

import alektas.common.ui.TermCard
import alektas.common.ui.models.TermItem
import alektas.midic.theme.AppTheme
import alektas.midic.theme.cornersXl
import alektas.midic.theme.paddingM
import alektas.midic.theme.paddingXs
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import alektas.ui_components.bottom_sheet.BottomSheetScaffold
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn

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
                        sheetBackgroundColor = MaterialTheme.colorScheme.surfaceVariant,
                        sheetElevation = 0.dp,
                        sheetContent = {
                            LazyColumn(
                                modifier = Modifier.fillMaxSize(),
                                contentPadding = PaddingValues(paddingM)
                            ) {
                                items(50) {
                                    val item = TermItem(
                                        id = 0L,
                                        word = "Word",
                                        transcription = "/word/",
                                        definitions = listOf()
                                    )
                                    TermCard(
                                        modifier = Modifier.fillMaxWidth(),
                                        item = item
                                    )
                                    Spacer(modifier = Modifier.size(paddingXs))
                                }
                            }
                        },
                    ) {
                        Text("Background")
                    }
                }
            }
        }
    }
}
