package alektas.common.data.local.db.entities

import alektas.common.data.local.db.entities.BookmarkEntity.Companion.DB_COLUMN_BOOKMARKS_DEFINITION
import alektas.common.data.local.db.entities.BookmarkEntity.Companion.DB_COLUMN_BOOKMARKS_TERM_WORD
import androidx.room.*
import androidx.room.ForeignKey.CASCADE

@Entity(
    tableName = "bookmarks",
    foreignKeys = [
        ForeignKey(
            entity = TermEntity::class,
            parentColumns = [TermEntity.DB_COLUMN_TERMS_WORD],
            childColumns = [DB_COLUMN_BOOKMARKS_TERM_WORD],
            onDelete = CASCADE,
            onUpdate = CASCADE,
        ),
        ForeignKey(
            entity = DefinitionEntity::class,
            parentColumns = [DefinitionEntity.DB_COLUMN_DEFINITIONS_DEFINITION],
            childColumns = [DB_COLUMN_BOOKMARKS_DEFINITION],
            onDelete = CASCADE,
            onUpdate = CASCADE,
        )
    ],
    primaryKeys = [
        DB_COLUMN_BOOKMARKS_TERM_WORD,
        DB_COLUMN_BOOKMARKS_DEFINITION,
    ],
    indices = [
        Index(DB_COLUMN_BOOKMARKS_DEFINITION)
    ]
)
data class BookmarkEntity(
    @ColumnInfo(name = DB_COLUMN_BOOKMARKS_TERM_WORD)
    val termWord: String,
    @ColumnInfo(name = DB_COLUMN_BOOKMARKS_DEFINITION)
    val definition: String,
) {

    companion object {

        const val DB_COLUMN_BOOKMARKS_TERM_WORD = "term_word"
        const val DB_COLUMN_BOOKMARKS_DEFINITION = "definition"
    }
}
