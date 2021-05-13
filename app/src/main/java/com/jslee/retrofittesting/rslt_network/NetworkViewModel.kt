package com.jslee.retrofittesting.rslt_network

import android.util.Log
import androidx.lifecycle.ViewModel

class NetworkViewModel : ViewModel() {

    init {
        Log.d("jjslee", "FailViewModel created!")
    }


    override fun onCleared() {
        super.onCleared()
        Log.i("jjslee", "FailViewModel destroyed!")
    }

}