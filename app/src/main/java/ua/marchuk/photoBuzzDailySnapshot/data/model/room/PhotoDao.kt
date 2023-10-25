package ua.marchuk.photoBuzzDailySnapshot.data.model.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PhotoDao {
    @Query("SELECT * FROM photo")
    fun getPhotosLiveData(): LiveData<List<PhotoEntity>>

    @Query("SELECT * FROM photo")
    fun getPhotos(): List<PhotoEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPhotos(photos: List<PhotoEntity>)

    @Delete
    suspend fun deletePhoto(photo: PhotoEntity)

    @Query("DELETE FROM photo WHERE id NOT IN (:ids)")
    suspend fun deletePhotosExcept(ids: List<Long>)

}
