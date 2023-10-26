package ua.marchuk.photoBuzzDailySnapshot.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import ua.marchuk.photoBuzzDailySnapshot.data.model.Photo
import ua.marchuk.photobuzz_dailyshapshot.R
import ua.marchuk.photobuzz_dailyshapshot.databinding.ItemInListBinding

class PhotosAdapter(
    private val onClickListener: AdapterInterface
) : ListAdapter<Photo, PhotosAdapter.PhotoViewHolder>(PhotoDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemInListBinding.inflate(inflater, parent, false)
        return PhotoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val photo = getItem(position)
        holder.bind(photo)
    }

    inner class PhotoViewHolder(private val binding: ItemInListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(photo: Photo) {
            with(binding) {
                itemListImage.load(photo.url) {
                    crossfade(true)
                    placeholder(R.drawable.baseline_sync_24)
                    error(R.drawable.baseline_sync_disabled_24)
                }
                itemListImage.setOnClickListener {
                    onClickListener.onItemClick(photo)
                }
                itemListTitle.text = photo.title
            }
        }
    }
}





