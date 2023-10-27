package ua.marchuk.photoBuzzDailySnapshot.presentation.mainFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ua.marchuk.photoBuzzDailySnapshot.data.model.PhotoData
import ua.marchuk.photoBuzzDailySnapshot.data.repository.PhotoRepository
import javax.inject.Inject

@HiltViewModel
class MainFragmentViewModel @Inject constructor(
    private val photoRepository: PhotoRepository
) : ViewModel() {

    val photosLiveData: LiveData<List<PhotoData>> = photoRepository.photosLiveData

    init {
        viewModelScope.launch {
            photoRepository.getPhotos()
        }
    }
}