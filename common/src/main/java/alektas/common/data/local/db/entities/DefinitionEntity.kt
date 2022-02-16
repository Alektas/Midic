package alektas.common.data.local.db.entities

import alektas.common.data.local.db.entities.DefinitionEntity.Companion.DB_COLUMN_DEFINITIONS_TERM_WORD
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

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
    ]
)
data class DefinitionEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = DB_COLUMN_DEFINITIONS_ID)
    val id: Long = -1L,
    @ColumnInfo(name = DB_COLUMN_DEFINITIONS_TERM_WORD)
    val termWord: String,
    @ColumnInfo(name = "part_of_speech")
    val partOfSpeech: String,
    val definition: String,
    val example: String? = null,
    @ColumnInfo(name = "image_url")
    val imageUrl: String? = null
) {

    companion object {

        const val DB_COLUMN_DEFINITIONS_ID = "id"
        const val DB_COLUMN_DEFINITIONS_TERM_WORD = "term_word"
    }
}
