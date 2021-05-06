package com.jslee.retrofittesting.home

import android.util.Log
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    init {
        Log.d("jjslee", "HomeViewModel created!")
    }


    override fun onCleared() {
        super.onCleared()
        Log.i("jjslee", "HomeViewModel destroyed!")
    }

}