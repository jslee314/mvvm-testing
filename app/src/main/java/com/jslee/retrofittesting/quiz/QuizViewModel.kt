package com.jslee.retrofittesting.quiz

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class QuizViewModel: ViewModel()  {

    /**
    * @내용 : 점수 (캡슐화)
    * @작성자 : 이재선
    **/
    private val _score = MutableLiveData<Int>()
    val score:LiveData<Int>
        get() = _score

    /**
    * @내용 : 선택 완료 버튼 (캡슐화)
    * @작성자 : 이재선
    **/
    private val _eventSelectFinish = MutableLiveData<Boolean>()
    val eventSelectFinish:LiveData<Boolean>
        get() = _eventSelectFinish


    init {
        _score.value = 0
        Log.d("jjslee", "_score : " + _score.value)

    }

    fun onPlusScore(){
        Log.d("jjslee", "_score : " + _score.value)

        _score.value = (_score.value)?.plus(1)
        Log.d("jjslee", "_score : " + _score.value)

    }

    fun onSelectFinish(){
        _eventSelectFinish.value = true

    }


    override fun onCleared() {
        super.onCleared()
    }

}