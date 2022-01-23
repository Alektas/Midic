package alektas.midic

import alektas.common.ui.TermCard
import alektas.common.ui.models.TermItem
import alektas.midic.theme.AppTheme
import alektas.midic.theme.cornersXl
import alektas.midic.theme.paddingM
import alektas.midic.theme.paddingXs
import alektas.term_search.ui.SearchBar
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import alektas.ui_components.bottom_sheet.BottomSheetScaffold
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    BottomSheetScaffold(
                        modifier = Modifier.fillMaxSize(),
                        sheetShape = RoundedCornerShape(topStart = cornersXl, topEnd = cornersXl),
                        sheetExpandedStateEnabled = true,
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
                        var query by remember { mutableStateOf("") }
                        SearchBar(
                            query = query,
                            withVoiceInputButton = true,
                            onQueryChanged = {
                                query = it
                            }
                        )
                    }
                }
            }
        }
    }
}
