package com.jslee.mvvm_testing.video

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.jslee.mvvm_testing.data.AppRepository
import kotlinx.coroutines.launch
import java.io.IOException

class VideoViewModel (application: Application) : AndroidViewModel(application) {
    /**
     * The data source this ViewModel will fetch results from. */
    private val repository = AppRepository.getRepository(application)

    /**
     * A playlist of videos displayed on the screen. */
    val playlist = repository.videos

    /**
     * Event triggered for network error */
    private var _eventNetworkError = MutableLiveData<Boolean>(false)
    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    /**
     * Flag to display the error message.*/
    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)
    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown


    init {
        refreshDataFromRepository()
    }

    /**
     * 저장소에서 데이터를 새로 고칩니다.
     * coroutine launch를 사용하여 백그라운드 스레드에서 실행 */
    private fun refreshDataFromRepository() {
        viewModelScope.launch {
            try {
                repository.refreshVideos()
                _eventNetworkError.value = false
                _isNetworkErrorShown.value = false

            } catch (networkError: IOException) {
                // Show a Toast error message and hide the progress bar.
                if(playlist.value.isNullOrEmpty())
                    _eventNetworkError.value = true
            }
        }
    }

    /**
     * Resets the network error flag. */
    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }


}