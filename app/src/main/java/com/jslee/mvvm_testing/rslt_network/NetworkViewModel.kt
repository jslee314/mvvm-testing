package com.jslee.mvvm_testing.rslt_network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jslee.mvvm_testing.data.remote.MarsProperty
import com.jslee.mvvm_testing.util.retrofit.retrofitCallStatus
import com.jslee.mvvm_testing.util.retrofit.RetrofitApi
import kotlinx.coroutines.launch

class NetworkViewModel : ViewModel() {

    private val _status = MutableLiveData<retrofitCallStatus>() // 가장 최근 response를 저장하는 "내부" MutableLiveData
    val status: LiveData<retrofitCallStatus>  // "외부 불변" LiveData
        get() = _status

    private val _properties = MutableLiveData<List<MarsProperty>>()
    val properties: LiveData<List<MarsProperty>>
        get() = _properties

    init {
        getMarsRealEstateProperties()
    }

    private fun getMarsRealEstateProperties() {

        viewModelScope.launch {
            _status.value = retrofitCallStatus.LOADING

            try {
                _properties.value = RetrofitApi.retrofitService.getProperties()
                _status.value = retrofitCallStatus.DONE

            } catch (e: Exception) {
                _status.value = retrofitCallStatus.ERROR
                _properties.value = ArrayList() // empty list : clears RecyclerView

            }
        }

    }
}