package com.jslee.mvvm_testing.rslt_network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jslee.mvvm_testing.data.remote.MarsProperty
import com.jslee.mvvm_testing.util.retrofit.MarsApiFilter
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
        //getMarsRealEstateProperties()
        getMarsRealEstateProperties(MarsApiFilter.SHOW_ALL)
    }


    private fun getMarsRealEstateProperties(filter:MarsApiFilter ){
        viewModelScope.launch {
            _status.value = retrofitCallStatus.LOADING
            try {
                _properties.value = RetrofitApi.retrofitService.getProperties(filter.value)
                _status.value = retrofitCallStatus.DONE
            } catch (e: Exception) {
                _status.value = retrofitCallStatus.ERROR
                _properties.value = ArrayList()
            }
        }

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

    /**
     * [getMarsRealEstateProperties]를 호출하여 새 필터로 데이터를 쿼리하여
     * 웹 서비스에 대한 데이터 세트 필터를 업데이트합니다.
     * @param은 웹 서버 요청의 일부로 전송되는 [MarsApiFilter]를 필터링합니다.  */
    fun updateFilter(filter: MarsApiFilter) {
        getMarsRealEstateProperties(filter)
    }
}