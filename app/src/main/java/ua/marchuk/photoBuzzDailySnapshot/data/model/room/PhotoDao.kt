package ua.marchuk.photoBuzzDailySnapshot.data.model.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import ua.marchuk.photoBuzzDailySnapshot.domain.Photo

@Dao
interface PhotoDao {
    @androidx.room.Query("SELECT * FROM photo")
    fun getPhotosLiveData(): LiveData<List<PhotoDB>>

    @androidx.room.Query("SELECT * FROM photo")
    fun getPhotos(): List<PhotoDB>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPhotos(photos: List<PhotoDB>)

    @Delete
    suspend fun deletePhoto(photo: PhotoDB)
}
