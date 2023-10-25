package ua.marchuk.photoBuzzDailySnapshot.data.model.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [PhotoEntity::class], version = 1)
abstract class LocalDatabase : RoomDatabase() {

    abstract fun photoDao(): PhotoDao

}


