package ua.marchuk.photoBuzzDailySnapshot.data.api.flickr

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://www.flickr.com/"

object FlickrInstance {     //TODO to di
    private val instance: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val flickrService: FlickrService by lazy {
        instance.create(FlickrService::class.java)
    }
}
