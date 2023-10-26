package ua.marchuk.photoBuzzDailySnapshot.presentation.infoFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ua.marchuk.photoBuzzDailySnapshot.data.repository.PhotoInfoRepository
import javax.inject.Inject

@HiltViewModel
class InfoFragmentViewModel @Inject constructor(
    private val photoInfoRepository: PhotoInfoRepository
) : ViewModel() {

    val photoInfoLiveData: LiveData<String> = photoInfoRepository.photoInfoLiveData
    fun loadPhotoInfo(photoId: String) {
        viewModelScope.launch {
            photoInfoRepository.loadPhotoInfo(photoId)
        }
    }


}