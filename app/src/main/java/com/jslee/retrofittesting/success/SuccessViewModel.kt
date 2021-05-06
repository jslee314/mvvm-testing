package com.jslee.retrofittesting.success

import android.util.Log
import androidx.lifecycle.ViewModel

class SuccessViewModel(userScore: Int) : ViewModel()  {


    var score = userScore


    init {
    }


    override fun onCleared() {
        super.onCleared()
    }

}