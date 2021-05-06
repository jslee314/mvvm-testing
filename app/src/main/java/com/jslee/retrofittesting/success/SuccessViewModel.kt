package com.jslee.retrofittesting.success

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SuccessViewModel(userScore: Int) : ViewModel()  {

    /**
     * @내용 : 점수 (캡슐화)
     * @작성자 : 이재선
     **/
    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int>
        get() = _score

    /**
     * @내용 : 선택 완료 버튼 (캡슐화)
     * @작성자 : 이재선
     **/
    private val _eventClickSuccess = MutableLiveData<Boolean>()
    val eventClickSuccess:LiveData<Boolean>
        get() = _eventClickSuccess

    init {
        _score.value = userScore
    }

    fun onClickedSuccessBtn(){
        _eventClickSuccess.value = true

    }


    override fun onCleared() {
        super.onCleared()
    }

}