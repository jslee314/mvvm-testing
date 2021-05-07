package com.jslee.retrofittesting.home

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jslee.retrofittesting.db.dao.ScoreDao
import com.jslee.retrofittesting.db.dao.UserDao
import javax.xml.transform.Source

class HomeViewModelFactory(private val userDataSource: UserDao,
                           private val scoreDataSource: ScoreDao,
                           private val application: Application) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(userDataSource, scoreDataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}