package com.jslee.mvvm_testing.data.dataSource

import com.jslee.mvvm_testing.data.local.dao.ScoreDao
import com.jslee.mvvm_testing.data.local.dao.UserDao
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class LocalDataSource internal constructor(
    private val sourceDao: ScoreDao,
    private val userDao: UserDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : AppDataSource{



}