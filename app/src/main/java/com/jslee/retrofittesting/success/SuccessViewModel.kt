package com.jslee.retrofittesting.success

import android.util.Log
import androidx.lifecycle.ViewModel

class SuccessViewModel(userScore: Int) : ViewModel()  {


    var score = userScore


    init {
        Log.d("jjslee", "SuccessViewModel created!")
    }


    override fun onCleared() {
        super.onCleared()
        Log.i("jjslee", "SuccessViewModel destroyed!")
    }

}