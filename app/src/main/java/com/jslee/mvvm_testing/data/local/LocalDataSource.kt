package com.jslee.mvvm_testing.data.local

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
import com.jslee.mvvm_testing.data.vo.DevByteVideo
import com.jslee.mvvm_testing.util.api.VideoApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LocalDataSource internal constructor(
    private val userDao: UserDao,
    private val sourceDao: ScoreDao,
    private val videoDao: VideoDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : AppDataSource {

    override suspend fun selectLatestUser(): User? = withContext(ioDispatcher) {
        val user: User? = userDao.selectLatestUser()
        return@withContext user
    }

    override suspend fun insertUser(user: User) = withContext(ioDispatcher)  {
        userDao.insertUser(user)
    }

    override suspend fun deleteUser() = withContext(ioDispatcher)  {
        userDao.deleteUser()
    }

    override suspend fun getVideos(): LiveData<List<DevByteVideo>>{
        return Transformations.map(videoDao.getVideos()) {
            it.asDomainModel()
        }
    }

    override suspend fun insertAll() {
        val playlist = VideoApi.devbytes.getPlaylist()
        videoDao.insertAll(playlist.asDatabaseModel())
    }


    override suspend fun insertScore(score: Score) {
        sourceDao.insertScore(score)
    }

    override suspend fun selectLatestScore(): Score? {
        return sourceDao.selectLatestScore()
    }


}