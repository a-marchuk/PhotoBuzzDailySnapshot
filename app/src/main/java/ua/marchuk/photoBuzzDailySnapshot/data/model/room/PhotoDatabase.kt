package ua.marchuk.photoBuzzDailySnapshot.data.model.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [PhotoDB::class], version = 1)
abstract class PhotoDatabase : RoomDatabase() {

    abstract fun photoDao(): PhotoDao

}