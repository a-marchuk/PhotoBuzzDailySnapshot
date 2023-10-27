package ua.marchuk.photoBuzzDailySnapshot.utility

import ua.marchuk.photoBuzzDailySnapshot.data.local.room.PhotoDescriptionEntity
import ua.marchuk.photoBuzzDailySnapshot.data.model.Photo
import ua.marchuk.photoBuzzDailySnapshot.data.local.room.PhotoEntity
import ua.marchuk.photoBuzzDailySnapshot.data.model.PhotoDescription

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
fun PhotoDescriptionEntity.fromEntity(): PhotoDescription {
    return PhotoDescription(
        id = id,
        description = description
    )
}
fun PhotoDescription.toEntity(): PhotoDescriptionEntity {
    return PhotoDescriptionEntity(
        id = id,
        description = description
    )
}