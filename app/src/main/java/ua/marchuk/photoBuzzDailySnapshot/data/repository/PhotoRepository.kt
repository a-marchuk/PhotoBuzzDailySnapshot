package ua.marchuk.photoBuzzDailySnapshot.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import ua.marchuk.photoBuzzDailySnapshot.data.api.apiresponse.PhotoInfoResponse
import ua.marchuk.photoBuzzDailySnapshot.data.api.apiresponse.PhotoResponse
import ua.marchuk.photoBuzzDailySnapshot.data.api.apiresponse.PhotosSearchResponse
import ua.marchuk.photoBuzzDailySnapshot.data.api.flickr.FlickrInstance.flickrService
import ua.marchuk.photoBuzzDailySnapshot.data.local.room.PhotoDao
import ua.marchuk.photoBuzzDailySnapshot.data.model.Photo
import ua.marchuk.photoBuzzDailySnapshot.data.model.PhotoData
import ua.marchuk.photoBuzzDailySnapshot.data.model.PhotoDescription
import ua.marchuk.photoBuzzDailySnapshot.utility.Constants.Companion.API_KEY
import ua.marchuk.photoBuzzDailySnapshot.utility.Constants.Companion.TAG
import ua.marchuk.photoBuzzDailySnapshot.utility.fromEntity
import ua.marchuk.photoBuzzDailySnapshot.utility.toEntity
import javax.inject.Inject

class PhotoRepository @Inject constructor(
    private val photoDao: PhotoDao
) {

    val photosLiveData: MutableLiveData<List<PhotoData>> = MutableLiveData()

    suspend fun getPhotos() {
        loadPhotos()

        val photos = getPhotosBlocking()
        val descriptions = getPhotosDescriptionsBlocking()

        val photoDataList = photos.zip(descriptions) { photo, description ->
            PhotoData(photo, description)
        }

        photosLiveData.postValue(photoDataList)
    }

    private suspend fun getPhotosBlocking(): List<Photo> {
        return withContext(Dispatchers.IO) {
            photoDao.getPhotos().map { it.fromEntity() }
        }
    }

    private suspend fun getPhotosDescriptionsBlocking(): List<PhotoDescription> {
        return withContext(Dispatchers.IO) {
            photoDao.getPhotosDescriptions().map { it.fromEntity() }
        }
    }

    private suspend fun updatePhotos(localPhotos: List<Photo>, newPhotos: List<Photo>) {
        val existingPhotoIds = localPhotos.map { it.id }

        val newPhotosToInsert = mutableListOf<Photo>()
        val newDescriptionsToInsert = mutableListOf<PhotoDescription>()

        for (photo in newPhotos) {
            if (photo.id !in existingPhotoIds) {
                newPhotosToInsert.add(photo)
                val description = loadPhotoInfo(photo.id.toString())
                val descriptionEntity = PhotoDescription(id = photo.id, description = description)
                newDescriptionsToInsert.add(descriptionEntity)
            }
        }

        if (newPhotosToInsert.isNotEmpty()) {
            photoDao.insertPhotos(newPhotosToInsert.map { it.toEntity() })
            photoDao.insertPhotosDescriptions(newDescriptionsToInsert.map { it.toEntity() })
        }

        if (localPhotos.size + newPhotosToInsert.size > 30) {
            val photosToKeep = localPhotos.sortedByDescending { it.timestamp }
                .subList(0, minOf(30, localPhotos.size))
            val idsToKeep = photosToKeep.map { it.id }
            photoDao.deletePhotosExcept(idsToKeep)
            photoDao.deletePhotosDescriptionExcept(idsToKeep)
        }
    }

    private suspend fun loadPhotos() {
        try {
            val photoResponse = fetchInterestingPhotos()

            if (photoResponse != null) {
                val localPhotos = getPhotosBlocking()
                val newPhotos = convertToPhotos(photoResponse.photos.photo)
                updatePhotos(localPhotos, newPhotos)
                Log.e(TAG, "Success")
            }
        } catch (e: Exception) {
            Log.e("$TAG Exception", "Exception occurred", e)
        }
    }

    private suspend fun loadPhotoInfo(photoId: String): String {
        return try {
            val photoInfoResponse = fetchPhotosInfo(photoId)

            if (photoInfoResponse?.isSuccessful == true) {
                val photoInfo = photoInfoResponse.body()
                photoInfo?.let {
                    return it.photo.description._content
                }
            }
            ""
        } catch (e: Exception) {
            Log.e("$TAG Exception", "Exception occurred", e)
            ""
        }
    }

    private suspend fun fetchInterestingPhotos(): PhotosSearchResponse? {
        return try {
            val response = withContext(Dispatchers.IO) {
                flickrService.getInterestingPhotosList(
                    method = "flickr.interestingness.getList",
                    apiKey = API_KEY,
                    extras = "url_h, date_upload",
                    per_page = "30",
                    format = "json",
                    noJsonCallback = 1
                ).execute()
            }
            if (response.isSuccessful) {
                return response.body()
            } else {
                Log.e(TAG, "Unsuccessful API response")
                null
            }
        } catch (e: Exception) {
            Log.e("$TAG Exception", "Exception occurred", e)
            null
        }
    }

    private suspend fun fetchPhotosInfo(photoId: String): Response<PhotoInfoResponse>? {
        return try {
            withContext(Dispatchers.IO) {
                flickrService.getPhotoInfo(
                    method = "flickr.photos.getInfo",
                    apiKey = API_KEY,
                    photo_id = photoId,
                    format = "json",
                    noJsonCallback = 1
                ).execute()
            }
        } catch (e: Exception) {
            Log.e("$TAG Exception", "Exception occurred", e)
            null
        }
    }

    private fun convertToPhotos(photoResponseList: List<PhotoResponse>): List<Photo> {
        return photoResponseList.map { photoResponse ->
            Photo(
                id = photoResponse.id.toLong(),
                url = createPhotoUrl(photoResponse),
                title = photoResponse.title,
                timestamp = photoResponse.dateupload.toLong()
            )
        }
    }

    private fun createPhotoUrl(photoResponse: PhotoResponse): String {
        return "https://farm${photoResponse.farm}.staticflickr.com/${photoResponse.server}/" +
                "${photoResponse.id}_${photoResponse.secret}.jpg"
    }
}
