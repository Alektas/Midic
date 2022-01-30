package alektas.common.domain

data class Definition(
    val id: Long,
    val partOfSpeech: String,
    val definition: String,
    val example: String? = null,
    val imageUrl: String? = null
)
