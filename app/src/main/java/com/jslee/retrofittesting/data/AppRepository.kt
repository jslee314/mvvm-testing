package com.jslee.retrofittesting.data

import android.app.Application
import com.jslee.retrofittesting.data.dataSource.AppDataSource
import com.jslee.retrofittesting.data.dataSource.LocalDataSource
import com.jslee.retrofittesting.data.dataSource.RemoteDataSource
import com.jslee.retrofittesting.data.local.RoomDB

class AppRepository private constructor(application: Application) {
    private val remoteDataSource: AppDataSource
    private val localDataSource: AppDataSource



    init {
        val database = RoomDB.getInstance(application)

        remoteDataSource = RemoteDataSource
        localDataSource = LocalDataSource(database.scoreDao, database.userDao)
    }

}