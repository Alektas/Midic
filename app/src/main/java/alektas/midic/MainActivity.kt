package alektas.midic

import alektas.common.ui.models.DefinitionItem
import alektas.common.ui.models.TermItem
import alektas.midic.theme.AppTheme
import alektas.term_search.ui.SearchBar
import alektas.term_search.ui.TermSearchBottomSheet
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.*
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
                    TermSearchBottomSheet(
                        modifier = Modifier.fillMaxSize(),
                        terms = buildList {
                            repeat(50) {
                                val item = TermItem(
                                    id = it.toLong(),
                                    word = "Word $it",
                                    transcription = "/word $it/",
                                    definitions = buildList {
                                        repeat(it) {
                                            add(DefinitionItem(it.toLong(), "noun", "Definition"))
                                        }
                                    }
                                )
                                add(item)
                            }
                        },
                        onTermClick = {}
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
