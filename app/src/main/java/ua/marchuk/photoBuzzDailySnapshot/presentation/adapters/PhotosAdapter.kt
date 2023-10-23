package ua.marchuk.photoBuzzDailySnapshot.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import ua.marchuk.photoBuzzDailySnapshot.domain.Photo
import ua.marchuk.photobuzz_dailyshapshot.R

class PhotosAdapter : RecyclerView.Adapter<PhotosAdapter.PhotoViewHolder>() {
    private var photoList: List<Photo> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.item_in_list, parent, false)
        return PhotoViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val photo = photoList[position]
        holder.bind(photo)
    }

    override fun getItemCount(): Int {
        return photoList.size
    }

    fun setPhotoList(photos: List<Photo>) {
        photoList = photos
        notifyDataSetChanged()
    }

    inner class PhotoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val photoImageView: ImageView = itemView.findViewById(R.id.item_list_image)
        private val titleTextView: TextView = itemView.findViewById(R.id.item_list_title)

        fun bind(photo: Photo) {
            // Встановлення зображення та тексту для кожного елемента
            photoImageView.load(photo.url) {
                // Опції Coil для зображення (змініть їх за потребою)
                crossfade(true) // Застосовувати згортання при зміні зображення
                placeholder(R.drawable.baseline_sync_24) // Зображення-заповнювач поки завантажується
                error(R.drawable.baseline_sync_disabled_24) // Зображення помилки, якщо завантаження не вдалося
            }
            titleTextView.text = photo.title
        }
    }
}

