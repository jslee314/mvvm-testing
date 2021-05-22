package com.jslee.mvvm_testing.video

import android.app.Application
import androidx.lifecycle.*
import com.jslee.mvvm_testing.data.AppRepository
import com.jslee.mvvm_testing.data.local.entity.Score
import com.jslee.mvvm_testing.data.vo.DevByteVideo
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

class VideoViewModel @Inject constructor(
    private val repository: AppRepository
) : ViewModel() {

    /**
     * A playlist of videos displayed on the screen. */
    lateinit var playlist:LiveData<List<DevByteVideo>>

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
        getPlaylistFromRoomDB()
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

    /**
     *  viewModelScope 사용 함수들     */
    fun getPlaylistFromRoomDB(){
        viewModelScope.launch {
            playlist = getPlaylist()
        }
    }
    /**
     *  DB에 데이터 삽입 삭제 등과 관련된 함수들
     *  이 함수들은 suspend 함수들로
     *  코틀린을 사용해서 비동기 처리를 함*/
    private suspend fun getPlaylist(): LiveData<List<DevByteVideo>> {
        val videos = repository.getVideos()
        return videos
    }


}