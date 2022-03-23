package alektas.common.data.local.db.models

import alektas.common.data.local.db.entities.BookmarkEntity
import alektas.common.data.local.db.entities.DefinitionEntity
import alektas.common.data.local.db.entities.TermEntity
import androidx.room.Embedded
import androidx.room.Relation

data class BookmarkTerm(
    @Embedded
    val bookmark: BookmarkEntity,
    @Relation(
        parentColumn = BookmarkEntity.DB_COLUMN_BOOKMARKS_TERM_WORD,
        entityColumn = TermEntity.DB_COLUMN_TERMS_WORD
    )
    val term: TermEntity,
    @Relation(
        parentColumn = BookmarkEntity.DB_COLUMN_BOOKMARKS_DEFINITION,
        entityColumn = DefinitionEntity.DB_COLUMN_DEFINITIONS_DEFINITION
    )
    val definitions: List<DefinitionEntity>
)
