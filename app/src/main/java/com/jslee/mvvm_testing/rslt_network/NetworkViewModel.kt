package com.jslee.mvvm_testing.rslt_network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jslee.mvvm_testing.util.retrofit.RetrofitApi
import kotlinx.coroutines.launch

class NetworkViewModel : ViewModel() {

    // 가장 최근 response를 저장하는 "내부" MutableLiveData 문자열
    private val _response = MutableLiveData<String>()
    // response에 대한 "외부 불변" LiveData
    val response: LiveData<String>
        get() = _response

    init {
        getMarsRealEstateProperties()
    }
    private fun getMarsRealEstateProperties() {
//        _response.value = "Set the Mars API Response here!"

        viewModelScope.launch {
            try {
                val listResult = RetrofitApi.retrofitService.getProperties()
                _response.value = "Success: ${listResult.size} Mars properties retrieved"
            } catch (e: Exception) {
                _response.value = "Failure: ${e.message}"
            }
        }
    }

}