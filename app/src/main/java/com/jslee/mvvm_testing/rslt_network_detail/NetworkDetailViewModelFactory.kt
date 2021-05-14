package com.jslee.mvvm_testing.rslt_network_detail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jslee.mvvm_testing.data.remote.MarsProperty

class NetworkDetailViewModelFactory (private val marsProperty: MarsProperty,
                                     private val application: Application) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NetworkDetailViewModel::class.java)) {
            return NetworkDetailViewModel(marsProperty, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}