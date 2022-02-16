package alektas.common.data.local.db.entities

import androidx.room.*
import androidx.room.ForeignKey.CASCADE

@Entity(
    tableName = "bookmarks",
    foreignKeys = [
        ForeignKey(
            entity = TermEntity::class,
            parentColumns = [TermEntity.DB_COLUMN_TERMS_WORD],
            childColumns = ["term_word"],
            onDelete = CASCADE,
            onUpdate = CASCADE,
        ),
        ForeignKey(
            entity = DefinitionEntity::class,
            parentColumns = [DefinitionEntity.DB_COLUMN_DEFINITIONS_ID],
            childColumns = ["definition_id"],
            onDelete = CASCADE,
            onUpdate = CASCADE,
        )
    ]
)
data class BookmarkEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = -1L,
    @ColumnInfo(name = DB_COLUMN_BOOKMARKS_TERM_WORD)
    val termWord: String,
    @ColumnInfo(name = DB_COLUMN_BOOKMARKS_DEFINITION_ID)
    val definitionId: Long,
) {

    companion object {

        const val DB_COLUMN_BOOKMARKS_TERM_WORD = "term_word"
        const val DB_COLUMN_BOOKMARKS_DEFINITION_ID = "definition_id"
    }
}
