package ua.marchuk.photoBuzzDailySnapshot.utility

import ua.marchuk.photoBuzzDailySnapshot.data.model.room.PhotoDB
import ua.marchuk.photoBuzzDailySnapshot.domain.Photo

fun Photo.toEntity() :PhotoDB {
    return PhotoDB(
        id = id,
        url = url,
        title = title
    )
}

fun PhotoDB.fromEntity() :Photo {
    return Photo(
        id = id,
        url = url,
        title = title
    )
}