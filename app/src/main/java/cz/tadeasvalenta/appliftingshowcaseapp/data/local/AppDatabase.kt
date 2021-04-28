package cz.tadeasvalenta.appliftingshowcaseapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import cz.tadeasvalenta.appliftingshowcaseapp.data.entity.Capsule
import cz.tadeasvalenta.appliftingshowcaseapp.data.entity.Landpad
import cz.tadeasvalenta.appliftingshowcaseapp.util.DatabaseConverters

@Database(entities = [Capsule::class, Landpad::class], version = 1, exportSchema = false)
@TypeConverters(DatabaseConverters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun capsuleDao(): CapsuleDao

    abstract fun landpadDao(): LandpadDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase =
            instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also {
                    instance = it
                }
            }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, AppDatabase::class.java, "spaceX")
                .fallbackToDestructiveMigration()
                .build()
    }
}
