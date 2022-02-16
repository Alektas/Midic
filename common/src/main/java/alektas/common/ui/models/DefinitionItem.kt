package alektas.common.ui.models

data class DefinitionItem(
    val id: Long,
    val partOfSpeech: String,
    val definition: String,
    val inBookmarks: Boolean,
    val example: String? = null,
    val imageUrl: String? = null,
)
