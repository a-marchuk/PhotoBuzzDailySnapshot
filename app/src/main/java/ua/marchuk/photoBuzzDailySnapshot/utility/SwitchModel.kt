package ua.marchuk.photoBuzzDailySnapshot.utility

import ua.marchuk.photoBuzzDailySnapshot.data.model.Photo
import ua.marchuk.photoBuzzDailySnapshot.data.model.room.PhotoEntity

fun Photo.toEntity(): PhotoEntity {
    return PhotoEntity(
        id = id,
        url = url,
        title = title,
        timestamp = timestamp
    )
}

fun PhotoEntity.fromEntity(): Photo {
    return Photo(
        id = id,
        url = url,
        title = title,
        timestamp = timestamp
    )
}