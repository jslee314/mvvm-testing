package com.jslee.retrofittesting.quiz

import android.util.Log
import androidx.lifecycle.ViewModel

class QuizViewModel: ViewModel()  {

    init {
        Log.d("jjslee", "QuizViewModel created!")
    }


    override fun onCleared() {
        super.onCleared()
        Log.i("jjslee", "QuizViewModel destroyed!")
    }

}