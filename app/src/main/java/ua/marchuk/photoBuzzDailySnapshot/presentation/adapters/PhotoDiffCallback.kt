package ua.marchuk.photoBuzzDailySnapshot.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import ua.marchuk.photoBuzzDailySnapshot.data.model.Photo

class PhotoDiffCallback : DiffUtil.ItemCallback<Photo>() {
    override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
        return oldItem == newItem
    }
}
