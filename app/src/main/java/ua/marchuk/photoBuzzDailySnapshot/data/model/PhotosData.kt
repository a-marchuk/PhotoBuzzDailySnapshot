package ua.marchuk.photoBuzzDailySnapshot.data.model

import java.io.Serializable

data class PhotoData(
    val photo: Photo,
    val description: PhotoDescription
) : Serializable
