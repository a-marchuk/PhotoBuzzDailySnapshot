package ua.marchuk.photoBuzzDailySnapshot.data.api.apiresponse

import com.google.gson.annotations.SerializedName

// The outermost wrapper for the api response
data class PhotosSearchResponse(
    @SerializedName("photos") val photos: PhotosMetaData,
    @SerializedName("stat") val stat: String
)

data class PhotosMetaData(
    val page: Int,
    val pages: Int,
    val perpage: Int,
    val photo: List<PhotoResponse>,
    val total: Int
)

data class PhotoResponse(
     val dateupload: String,
     val farm: Int,
     val height_h: Int,
     val id: String,
     val isfamily: Int,
     val isfriend: Int,
     val ispublic: Int,
     val owner: String,
     val secret: String,
     val server: String,
     val title: String,
     val url_h: String,
     val width_h: Int
)


