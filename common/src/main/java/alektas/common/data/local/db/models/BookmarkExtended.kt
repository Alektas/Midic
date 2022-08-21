package alektas.common.data.local.db.models

import alektas.common.data.local.db.entities.BookmarkEntity
import alektas.common.data.local.db.entities.DefinitionEntity
import alektas.common.data.local.db.entities.TermEntity
import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class BookmarkExtended(
    @Embedded
    val term: TermEntity,
    @Relation(
        entity = DefinitionEntity::class,
        parentColumn = TermEntity.DB_COLUMN_TERMS_WORD,
        entityColumn = DefinitionEntity.DB_COLUMN_DEFINITIONS_DEFINITION,
        associateBy = Junction(
            value = BookmarkEntity::class,
            parentColumn = BookmarkEntity.DB_COLUMN_BOOKMARKS_TERM_WORD,
            entityColumn = BookmarkEntity.DB_COLUMN_BOOKMARKS_DEFINITION,
        )
    )
    val definitions: List<DefinitionEntity>
)
