package ua.marchuk.photoBuzzDailySnapshot.presentation.mainFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ua.marchuk.photoBuzzDailySnapshot.data.model.PhotoData
import ua.marchuk.photoBuzzDailySnapshot.presentation.adapters.AdapterInterface
import ua.marchuk.photoBuzzDailySnapshot.presentation.adapters.PhotosAdapter
import ua.marchuk.photobuzz_dailyshapshot.databinding.FragmentMainBinding

@AndroidEntryPoint
class MainFragment : Fragment() {

    private val viewModel: MainFragmentViewModel by viewModels()
    private lateinit var binding: FragmentMainBinding
    private lateinit var adapter: PhotosAdapter
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = findNavController()
        initializeRecyclerView()
        observePhotosLiveData()
    }

    private fun initializeRecyclerView() {
        adapter = PhotosAdapter(object : AdapterInterface {
            override fun onItemClick(photoData: PhotoData) {
                navigateToInfoFragment(photoData)
            }
        })
        binding.recyclerViewMain.adapter = adapter
    }

    private fun observePhotosLiveData() {
        viewModel.photosLiveData.observe(viewLifecycleOwner) { photosData ->
            adapter.submitList(photosData)
        }
    }

    private fun navigateToInfoFragment(photoData: PhotoData) {
        navController.navigate(MainFragmentDirections.actionMainFragmentToInfoFragment(photoData))
    }
}



