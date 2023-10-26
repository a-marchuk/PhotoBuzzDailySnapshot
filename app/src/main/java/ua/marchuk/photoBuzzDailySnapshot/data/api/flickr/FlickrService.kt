package ua.marchuk.photoBuzzDailySnapshot.data.api.flickr

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import ua.marchuk.photoBuzzDailySnapshot.data.api.apiresponse.PhotoInfoResponse
import ua.marchuk.photoBuzzDailySnapshot.data.api.apiresponse.PhotosSearchResponse

interface FlickrService {
    @GET("services/rest")
    fun getInterestingPhotosList(
        @Query("method") method: String,
        @Query("api_key") apiKey: String,
        @Query("extras") extras: String,
        @Query("per_page") per_page: String,
        @Query("format") format: String,
        @Query("nojsoncallback") noJsonCallback: Int
    ): Call<PhotosSearchResponse>

    @GET("services/rest")
    fun getPhotoInfo(
        @Query("method") method: String,
        @Query("api_key") apiKey: String,
        @Query("photo_id") photo_id: String,
        @Query("format") format: String,
        @Query("nojsoncallback") noJsonCallback: Int
    ): Call<PhotoInfoResponse>
}