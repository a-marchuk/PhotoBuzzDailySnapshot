package ua.marchuk.photoBuzzDailySnapshot.presentation.adapters

import ua.marchuk.photoBuzzDailySnapshot.data.model.PhotoData

interface AdapterInterface {

    fun onItemClick(photoData: PhotoData)

}