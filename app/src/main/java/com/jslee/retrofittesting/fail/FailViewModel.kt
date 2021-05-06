package com.jslee.retrofittesting.fail

import android.util.Log
import androidx.lifecycle.ViewModel

class FailViewModel : ViewModel() {

    init {
        Log.d("jjslee", "FailViewModel created!")
    }


    override fun onCleared() {
        super.onCleared()
        Log.i("jjslee", "FailViewModel destroyed!")
    }

}