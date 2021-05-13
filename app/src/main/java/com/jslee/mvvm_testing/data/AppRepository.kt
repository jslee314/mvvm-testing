package com.jslee.mvvm_testing.data

import android.app.Application
import com.jslee.mvvm_testing.data.dataSource.AppDataSource
import com.jslee.mvvm_testing.data.dataSource.LocalDataSource
import com.jslee.mvvm_testing.data.dataSource.RemoteDataSource
import com.jslee.mvvm_testing.data.local.RoomDB

class AppRepository private constructor(application: Application) {
    private val remoteDataSource: AppDataSource
    private val localDataSource: AppDataSource



    init {
        val database = RoomDB.getInstance(application)

        remoteDataSource = RemoteDataSource
        localDataSource = LocalDataSource(database.scoreDao, database.userDao)
    }

}