package com.jslee.mvvm_testing.ground

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jslee.mvvm_testing.data.AppRepository
import com.jslee.mvvm_testing.data.remote.GroundProperty
import com.jslee.mvvm_testing.data.GroundApiFilter
import com.jslee.mvvm_testing.data.vo.retrofitCallStatus
import com.jslee.mvvm_testing.util.api.GroundApi
import kotlinx.coroutines.launch
import javax.inject.Inject

class GroundViewModel  @Inject constructor(
    private val repository: AppRepository
) : ViewModel() {

    private val _status = MutableLiveData<retrofitCallStatus>() // 가장 최근 response를 저장하는 "내부" MutableLiveData
    val status: LiveData<retrofitCallStatus>  // "외부 불변" LiveData
        get() = _status

    private val _properties = MutableLiveData<List<GroundProperty>>()
    val properties: LiveData<List<GroundProperty>>
        get() = _properties

    /**
     * 다음 프레그 */
    private val _navigateToSelectedProperty = MutableLiveData<GroundProperty>()
    val navigateToSelectedProperty: LiveData<GroundProperty>
        get() = _navigateToSelectedProperty

    init {
        //getMarsRealEstateProperties()
        getGroundRealEstateProperties(GroundApiFilter.SHOW_ALL)
    }


    private fun getGroundRealEstateProperties(filter: GroundApiFilter){
        viewModelScope.launch {
            _status.value = retrofitCallStatus.LOADING
            try {
                _properties.value = GroundApi.retrofitService.getProperties(filter.value)
                _status.value = retrofitCallStatus.DONE
            } catch (e: Exception) {
                _status.value = retrofitCallStatus.ERROR
                _properties.value = ArrayList()
            }
        }

    }


    private fun getGroundRealEstateProperties() {
        viewModelScope.launch {
            _status.value = retrofitCallStatus.LOADING

            try {
                _properties.value = GroundApi.retrofitService.getProperties()
                _status.value = retrofitCallStatus.DONE

            } catch (e: Exception) {
                _status.value = retrofitCallStatus.ERROR
                _properties.value = ArrayList() // empty list : clears RecyclerView
            }
        }
    }

    /**
     * [getGroundRealEstateProperties]를 호출하여 새 필터로 데이터를 쿼리하여
     * 웹 서비스에 대한 데이터 세트 필터를 업데이트합니다.
     * @param은 웹 서버 요청의 일부로 전송되는 [GroundApiFilter]를 필터링합니다.  */
    fun updateFilter(filter: GroundApiFilter) {
        getGroundRealEstateProperties(filter)
    }

    /**
     * RecyclerView의 하나의 아이템을 클릭하면
     * [_navigateToSelectedProperty] [MutableLiveData]를 설정한다.
     * [groundProperty]:  클릭 된 GroundProperty */
    fun displayPropertyDetails(groundProperty: GroundProperty) {
        _navigateToSelectedProperty.value = groundProperty
    }

    /**
     * Navigation이 수행 되면 [_navigateToSelectedProperty] 를 null로 설정      */
    fun displayPropertyDetailsComplete() {
        _navigateToSelectedProperty.value = null
    }
}