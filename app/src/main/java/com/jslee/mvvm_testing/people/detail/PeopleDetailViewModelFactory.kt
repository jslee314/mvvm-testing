package com.jslee.mvvm_testing.people.detail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jslee.mvvm_testing.data.remote.PeopleDetail

class PeopleDetailViewModelFactory (private val peopleDetail: PeopleDetail,
                                    private val application: Application) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PeopleDetailViewModel::class.java)) {
            return PeopleDetailViewModel(
                    peopleDetail,
                application
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}