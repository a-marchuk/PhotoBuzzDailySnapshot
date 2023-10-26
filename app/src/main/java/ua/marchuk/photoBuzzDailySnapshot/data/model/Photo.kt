package ua.marchuk.photoBuzzDailySnapshot.data.model

import java.io.Serializable

data class Photo(
    val id: Long,
    val url: String,
    val title: String,
    val timestamp: Long,
) : Serializable