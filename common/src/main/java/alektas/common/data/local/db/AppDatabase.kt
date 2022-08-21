package alektas.common.data.local.db

import alektas.common.data.local.db.dao.BookmarksDao
import alektas.common.data.local.db.entities.BookmarkEntity
import alektas.common.data.local.db.entities.DefinitionEntity
import alektas.common.data.local.db.entities.TermEntity
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        TermEntity::class,
        DefinitionEntity::class,
        BookmarkEntity::class
    ],
    version = 1,
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun bookmarksDao(): BookmarksDao

}
