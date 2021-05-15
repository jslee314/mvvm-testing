package com.jslee.mvvm_testing.video

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * 매개 변수로  VideoViewModel을 구성하기위한 Factory */
class VideoViewModelFactory (val app: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(VideoViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return VideoViewModel(app) as T
        }
        throw IllegalArgumentException("Unable to construct viewmodel")
    }
}