package ua.marchuk.photoBuzzDailySnapshot.data.model

data class Photo(
    val id: Long,
    val url: String,
    val title: String,
    val timestamp: Long,
)