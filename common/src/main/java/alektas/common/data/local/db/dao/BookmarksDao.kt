package alektas.common.data.local.db.dao

import alektas.common.data.local.db.entities.BookmarkEntity
import alektas.common.data.local.db.entities.DefinitionEntity
import alektas.common.data.local.db.entities.TermEntity
import alektas.common.data.local.db.models.BookmarkExtended
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
            definition = definition.definition,
        )
        saveBookmark(bookmark)
    }

    @Query("DELETE FROM bookmarks WHERE definition = :definition")
    abstract suspend fun deleteDefinition(definition: String)

    @Transaction
    @Query("SELECT * FROM terms")
    abstract fun observeBookmarks(): Flow<List<BookmarkExtended>>

    @Insert(onConflict = REPLACE)
    protected abstract suspend fun saveBookmark(bookmark: BookmarkEntity)

    @Insert(onConflict = IGNORE)
    protected abstract suspend fun save(termEntity: TermEntity)

    @Insert(onConflict = REPLACE)
    protected abstract suspend fun save(definitionEntity: DefinitionEntity)

}
