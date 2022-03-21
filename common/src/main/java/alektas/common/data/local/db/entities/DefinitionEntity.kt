package alektas.common.data.local.db.entities

import alektas.common.data.local.db.entities.DefinitionEntity.Companion.DB_COLUMN_DEFINITIONS_TERM_WORD
import androidx.room.*
import androidx.room.ForeignKey.CASCADE

@Entity(
    tableName = "definitions",
    foreignKeys = [
        ForeignKey(
            entity = TermEntity::class,
            parentColumns = [TermEntity.DB_COLUMN_TERMS_WORD],
            childColumns = [DB_COLUMN_DEFINITIONS_TERM_WORD],
            onDelete = CASCADE,
            onUpdate = CASCADE,
            deferred = true
        )
    ],
    indices = [
        Index(DB_COLUMN_DEFINITIONS_TERM_WORD)
    ]
)
data class DefinitionEntity(
    @PrimaryKey
    @ColumnInfo(name = DB_COLUMN_DEFINITIONS_DEFINITION)
    val definition: String,
    @ColumnInfo(name = DB_COLUMN_DEFINITIONS_TERM_WORD)
    val termWord: String,
    @ColumnInfo(name = "part_of_speech")
    val partOfSpeech: String,
    val example: String? = null,
    @ColumnInfo(name = "image_url")
    val imageUrl: String? = null
) {

    companion object {

        const val DB_COLUMN_DEFINITIONS_TERM_WORD = "term_word"
        const val DB_COLUMN_DEFINITIONS_DEFINITION = "definition"
    }
}
