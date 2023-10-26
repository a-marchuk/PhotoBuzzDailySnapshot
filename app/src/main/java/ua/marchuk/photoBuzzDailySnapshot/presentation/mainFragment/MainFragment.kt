package ua.marchuk.photoBuzzDailySnapshot.presentation.mainFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import ua.marchuk.photoBuzzDailySnapshot.presentation.adapters.PhotosAdapter
import ua.marchuk.photobuzz_dailyshapshot.R

@AndroidEntryPoint
class MainFragment : Fragment() {

    private val viewModel: MainFragmentViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: PhotosAdapter

    private val spanCount = 3

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recycler_view_main)
        adapter = PhotosAdapter()

        recyclerView.layoutManager = GridLayoutManager(context, spanCount)
        recyclerView.adapter = adapter

        viewModel.photosLiveData.observe(viewLifecycleOwner) { photos ->
            adapter.setPhotoList(photos)
        }

//        adapter.onItemClick = { photo ->
//            // Використовуйте SafeArgs для передачі даних до InfoFragment
//            val action = MainFragmentDirections.actionMainFragmentToInfoFragment(photo)
//            findNavController().navigate(action)
//        }
    }

}

