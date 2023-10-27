package ua.marchuk.photoBuzzDailySnapshot.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [PhotoEntity::class, PhotoDescriptionEntity::class], version = 1)
abstract class LocalDatabase : RoomDatabase() {

    abstract fun photoDao(): PhotoDao
}



