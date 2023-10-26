package ua.marchuk.photoBuzzDailySnapshot.presentation.infoFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import coil.load
import dagger.hilt.android.AndroidEntryPoint
import ua.marchuk.photoBuzzDailySnapshot.data.model.Photo
import ua.marchuk.photobuzz_dailyshapshot.R
import ua.marchuk.photobuzz_dailyshapshot.databinding.FragmentInfoBinding

@AndroidEntryPoint
class InfoFragment : Fragment() {

    private val viewModel: InfoFragmentViewModel by viewModels()
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

        val photo = args.photo

        viewModel.loadPhotoInfo(photo.id.toString())

        viewModel.photoInfoLiveData.observe(viewLifecycleOwner) { description ->
            setInfo(photo, description)
        }


    }

    private fun setInfo(photo: Photo, description: String) {
        with(binding) {
            infoItemListImage.load(photo.url) {
                crossfade(true)
                placeholder(R.drawable.baseline_sync_24)
                error(R.drawable.baseline_sync_disabled_24)
            }
            infoItemListTitle.text = photo.title
            infoItemListText.text = description
        }
    }
}

