package ua.marchuk.photoBuzzDailySnapshot.presentation.mainFragment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ua.marchuk.photoBuzzDailySnapshot.data.repository.PhotoRepository
import ua.marchuk.photoBuzzDailySnapshot.data.model.Photo
import javax.inject.Inject

@HiltViewModel
class MainFragmentViewModel @Inject constructor(
    private val photoRepository: PhotoRepository
) : ViewModel() {

    val photosLiveData: LiveData<List<Photo>> = photoRepository.photosLiveData

    init {
        viewModelScope.launch {
            photoRepository.loadPhotos()
            Log.e("ViewModel", "ViewModel")

        }
    }
}