package alektas.common.domain

data class Definition(
    val partOfSpeech: String,
    val definition: String,
    val example: String? = null,
    val imageUrl: String? = null,
    val inBookmarks: Boolean,
)
