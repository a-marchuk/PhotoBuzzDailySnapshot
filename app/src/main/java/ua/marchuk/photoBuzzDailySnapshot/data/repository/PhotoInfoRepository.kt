package ua.marchuk.photoBuzzDailySnapshot.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ua.marchuk.photoBuzzDailySnapshot.data.api.flickr.FlickrInstance

class PhotoInfoRepository {
    val photoInfoLiveData: MutableLiveData<String> = MutableLiveData()

    suspend fun loadPhotoInfo(photoId: String) {
        try {
            val response = withContext(Dispatchers.IO) {
                FlickrInstance.flickrService.getPhotoInfo(
                    method = "flickr.photos.getInfo",
                    apiKey = API_KEY,
                    photo_id = photoId,
                    format = "json",
                    noJsonCallback = 1
                ).execute()
            }
            if (response.isSuccessful) {
                val photoInfoResponse = response.body()
                if (photoInfoResponse != null){
                    val description = photoInfoResponse.photo.description._content
                    photoInfoLiveData.postValue(description)
                    Log.e(TAG, description)
                }
            }
        } catch (e: Exception) {
            Log.e("$TAG Exception", "Exception occurred", e)
        }
    }

    companion object {
        private const val API_KEY = "531d11b4153d70488de3e69321772c9d"
        private const val TAG = "PhotoInfoRepository"
    }
}