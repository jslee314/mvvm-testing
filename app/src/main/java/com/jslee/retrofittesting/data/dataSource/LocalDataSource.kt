package com.jslee.retrofittesting.data.dataSource

import com.jslee.retrofittesting.data.local.dao.ScoreDao
import com.jslee.retrofittesting.data.local.dao.UserDao
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class LocalDataSource internal constructor(
    private val sourceDao: ScoreDao,
    private val userDao: UserDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : AppDataSource{



}