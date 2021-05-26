package com.jslee.mvvm_testing.people.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jslee.mvvm_testing.data.remote.PeopleDetail

class PeopleDetailViewModel (peopleDetail: PeopleDetail,
                             app: Application) : AndroidViewModel(app) {

    private val _selectedPeople = MutableLiveData<PeopleDetail>()
    val selectedPeople: LiveData<PeopleDetail>
        get() = _selectedPeople

    init {
        _selectedPeople.value = peopleDetail
    }

}