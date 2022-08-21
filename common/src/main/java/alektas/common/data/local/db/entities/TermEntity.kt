package alektas.common.data.local.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "terms",
)
data class TermEntity(
    @PrimaryKey
    @ColumnInfo(name = DB_COLUMN_TERMS_WORD)
    val word: String,
    val pronunciation: String,
) {

    companion object {
        const val DB_COLUMN_TERMS_WORD = "word"
    }

}
