package com.jslee.retrofittesting.success

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class SuccessViewModelFactory (private val userScore: Int) :ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(SuccessViewModel::class.java)){
            return SuccessViewModel(userScore) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")

    }


}