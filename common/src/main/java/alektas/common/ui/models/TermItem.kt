package alektas.common.ui.models

data class TermItem(
    val word: String,
    val transcription: String,
    val definitions: List<DefinitionItem> = listOf()
)
