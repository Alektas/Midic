package alektas.common.data.local.db.dao

import alektas.common.data.local.db.entities.BookmarkEntity
import alektas.common.data.local.db.entities.DefinitionEntity
import alektas.common.data.local.db.entities.TermEntity
import alektas.common.data.local.db.models.BookmarkTerm
import androidx.room.*
import androidx.room.OnConflictStrategy.*
import kotlinx.coroutines.flow.Flow

@Dao
abstract class BookmarksDao {

    @Transaction
    open suspend fun saveToBookmarks(term: TermEntity, definition: DefinitionEntity) {
        save(term)
        save(definition)
        val bookmark = BookmarkEntity(
            termWord = term.word,
            definitionId = definition.id,
        )
        saveBookmark(bookmark)
    }

    @Query("DELETE FROM bookmarks WHERE definition_id = :definitionId")
    abstract suspend fun deleteDefinition(definitionId: Long)

    @Transaction
    @Query("SELECT * FROM bookmarks")
    abstract fun observeBookmarks(): Flow<BookmarkTerm>

    @Insert(onConflict = IGNORE)
    protected abstract suspend fun saveBookmark(bookmark: BookmarkEntity)

    @Insert(onConflict = IGNORE)
    protected abstract suspend fun save(termEntity: TermEntity)

    @Insert(onConflict = IGNORE)
    protected abstract suspend fun save(definitionEntity: DefinitionEntity)

}
