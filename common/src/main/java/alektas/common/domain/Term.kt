package alektas.common.domain

data class Term(
    val word: String,
    val transcription: String,
    val definitions: List<Definition> = listOf()
)
