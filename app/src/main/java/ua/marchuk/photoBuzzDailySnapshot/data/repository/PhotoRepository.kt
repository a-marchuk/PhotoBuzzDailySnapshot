package ua.marchuk.photoBuzzDailySnapshot.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ua.marchuk.photoBuzzDailySnapshot.data.api.flickr.FlickrInstance
import ua.marchuk.photoBuzzDailySnapshot.data.model.apiresponse.PhotoResponse
import ua.marchuk.photoBuzzDailySnapshot.data.model.room.PhotoDao
import ua.marchuk.photoBuzzDailySnapshot.domain.Photo
import ua.marchuk.photoBuzzDailySnapshot.utility.fromEntity
import ua.marchuk.photoBuzzDailySnapshot.utility.toEntity
import javax.inject.Inject

class PhotoRepository @Inject constructor(
    private val photoDao: PhotoDao,
) {
    val photosLiveData: MutableLiveData<List<Photo>> = MutableLiveData()

    private suspend fun getPhotosBlocking(): List<Photo> {
        return withContext(Dispatchers.IO) {
            photoDao.getPhotos().map { it.fromEntity() }
        }
    }

    private suspend fun updatePhotos(localPhotos: List<Photo>, newPhotos: List<Photo>) {
        val newPhotosSet = HashSet(newPhotos)
        val updatedPhotos = ArrayList<Photo>()

        // Додайте нові фотографії
        for (photo in newPhotos) {
            if (!localPhotos.contains(photo)) {
                updatedPhotos.add(photo)
            }
        }

        // Видаліть застарі фотографії
        for (photo in localPhotos) {
            if (!newPhotosSet.contains(photo)) {
                photoDao.deletePhoto(photo.toEntity())
            }
        }

        // Вставте нові фотографії
        photoDao.insertPhotos(updatedPhotos.map { it.toEntity() })

        // Оновіть LiveData, щоб сповістити про зміни в даних
        withContext(Dispatchers.Main) {
            photosLiveData.postValue(updatedPhotos.toList())
        }
    }

    suspend fun loadPhotos() {
        try {
            val response = withContext(Dispatchers.IO) {
                FlickrInstance.flickrService.getInterestingPhotosList(
                    "flickr.interestingness.getList",
                    API_KEY,
                    "url_h",
                    "json",
                    1
                ).execute()
            }

            if (response.isSuccessful) {
                val photoResponse = response.body()
                if (photoResponse != null) {
                    val localPhotos = getPhotosBlocking()
                    val newPhotos = convertToPhotos(photoResponse.photos.photo)
                    updatePhotos(localPhotos, newPhotos)
                    Log.e(TAG, "Success")
                }
            } else {
                Log.e(TAG, "Unsuccessful API response")
            }
        } catch (e: Exception) {
            Log.e(TAG + "Exception", "Exception occurred", e)
        }
    }


    private fun convertToPhotos(photoResponseList: List<PhotoResponse>): List<Photo> {
        return photoResponseList.map { photoResponse ->
            val photo = Photo(
                id = photoResponse.id.toLong(),
                url = "https://farm${photoResponse.farm}.staticflickr.com/${photoResponse.server}/" +
                        "${photoResponse.id}_${photoResponse.secret}.jpg",
                title = photoResponse.title
            )

            Log.d("MyLink", "URL: ${photo.url}")

            photo
        }
    }

    companion object {
        private const val API_KEY = "531d11b4153d70488de3e69321772c9d"
        private const val TAG = "PhotoRepository"
    }
}




