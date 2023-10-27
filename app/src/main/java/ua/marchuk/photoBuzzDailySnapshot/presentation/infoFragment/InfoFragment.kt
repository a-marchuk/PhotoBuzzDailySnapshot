package ua.marchuk.photoBuzzDailySnapshot.presentation.infoFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import coil.load
import dagger.hilt.android.AndroidEntryPoint
import ua.marchuk.photoBuzzDailySnapshot.data.model.PhotoData
import ua.marchuk.photobuzz_dailyshapshot.R
import ua.marchuk.photobuzz_dailyshapshot.databinding.FragmentInfoBinding

@AndroidEntryPoint
class InfoFragment : Fragment() {

    private lateinit var binding: FragmentInfoBinding
    private val args: InfoFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val photoData = args.photoData

        setInfo(photoData)

    }

    private fun setInfo(photoData: PhotoData) {
        with(binding) {
            with(photoData) {
                infoItemListImage.load(photo.url) {
                    crossfade(true)
                    placeholder(R.drawable.baseline_sync_24)
                    error(R.drawable.baseline_sync_disabled_24)
                }
                infoItemListTitle.text = photo.title
                infoItemListText.text = description.description
            }

        }
    }
}

