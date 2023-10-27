package ua.marchuk.photoBuzzDailySnapshot.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import ua.marchuk.photoBuzzDailySnapshot.data.model.PhotoData

class PhotoDiffCallback : DiffUtil.ItemCallback<PhotoData>() {
    override fun areItemsTheSame(oldItem: PhotoData, newItem: PhotoData): Boolean {
        return oldItem.photo.id == newItem.photo.id
    }

    override fun areContentsTheSame(oldItem: PhotoData, newItem: PhotoData): Boolean {
        return oldItem == newItem
    }
}
