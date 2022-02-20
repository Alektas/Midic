package alektas.midic.di

import alektas.common.data.local.db.AppDatabase
import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
interface StorageModule {

    companion object {

        private const val DB_NAME = "midic_database"

        @Provides
        @Singleton
        fun provideAppDatabase(
            context: Context
        ): AppDatabase = Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME)
            .build()
    }

}
