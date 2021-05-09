package com.jslee.retrofittesting.success

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jslee.retrofittesting.database.dao.ScoreDao
import com.jslee.retrofittesting.database.dao.UserDao

class SuccessViewModelFactory (private val userDataSource: UserDao,
                               private val scoreDataSource: ScoreDao,
                               private val application: Application) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SuccessViewModel::class.java)) {
            return SuccessViewModel(userDataSource, scoreDataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
