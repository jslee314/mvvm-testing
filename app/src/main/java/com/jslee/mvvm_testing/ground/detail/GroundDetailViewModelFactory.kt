package com.jslee.mvvm_testing.ground.detail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jslee.mvvm_testing.data.remote.GroundProperty

class GroundDetailViewModelFactory (private val groundProperty: GroundProperty,
                                    private val application: Application) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GroundDetailViewModel::class.java)) {
            return GroundDetailViewModel(
                groundProperty,
                application
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}