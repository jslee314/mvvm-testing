package com.jslee.mvvm_testing.data.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.jslee.mvvm_testing.data.AppDataSource
import com.jslee.mvvm_testing.data.converter.asDatabaseModel
import com.jslee.mvvm_testing.data.converter.asDomainModel
import com.jslee.mvvm_testing.data.local.dao.ScoreDao
import com.jslee.mvvm_testing.data.local.dao.UserDao
import com.jslee.mvvm_testing.data.local.dao.VideoDao
import com.jslee.mvvm_testing.data.local.entity.Score
import com.jslee.mvvm_testing.data.local.entity.User
import com.jslee.mvvm_testing.data.local.entity.Video
import com.jslee.mvvm_testing.data.vo.DevByteVideo
import com.jslee.mvvm_testing.util.api.VideoApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RemoteDataSource internal constructor(
    private val userDao: UserDao,
    private val sourceDao: ScoreDao,
    private val videoDao: VideoDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : AppDataSource {
    override suspend fun selectLatestUser(): User? {
        TODO("Not yet implemented")
    }

    override suspend fun insertUser(user: User) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteUser() {
        TODO("Not yet implemented")
    }

    override suspend fun getVideos(): LiveData<List<DevByteVideo>> {
        TODO("Not yet implemented")
    }

    override suspend fun insertAll() {
        TODO("Not yet implemented")
    }

    override suspend fun insertScore(score: Score) {
        TODO("Not yet implemented")
    }

    override suspend fun selectLatestScore(): Score? {
        TODO("Not yet implemented")
    }
}