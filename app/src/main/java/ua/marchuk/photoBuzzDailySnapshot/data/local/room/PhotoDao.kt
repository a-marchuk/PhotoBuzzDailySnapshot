package ua.marchuk.photoBuzzDailySnapshot.data.local.room

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




    @Query("SELECT * FROM photo_description")
    fun getPhotosDescriptionsLiveData(): LiveData<List<PhotoDescriptionEntity>>

    @Query("SELECT * FROM photo_description")
    fun getPhotosDescriptions(): List<PhotoDescriptionEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPhotosDescriptions(photosDescriptions: List<PhotoDescriptionEntity>)

    @Delete
    suspend fun deletePhotoDescription(photoDescription: PhotoDescriptionEntity)

    @Query("DELETE FROM photo_description WHERE id NOT IN (:ids)")
    suspend fun deletePhotosDescriptionExcept(ids: List<Long>)


}
