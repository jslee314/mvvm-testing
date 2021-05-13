package com.jslee.retrofittesting.rslt_room

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jslee.retrofittesting.data.local.dao.ScoreDao
import com.jslee.retrofittesting.data.local.dao.UserDao

class RoomViewModelFactory (private val userDataSource: UserDao,
                            private val scoreDataSource: ScoreDao,
                            private val application: Application) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RoomViewModel::class.java)) {
            return RoomViewModel(userDataSource, scoreDataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
