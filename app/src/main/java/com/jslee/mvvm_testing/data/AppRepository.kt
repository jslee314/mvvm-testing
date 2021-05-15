package com.jslee.mvvm_testing.data

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.jslee.mvvm_testing.data.converter.asDatabaseModel
import com.jslee.mvvm_testing.data.converter.asDomainModel
import com.jslee.mvvm_testing.data.dataSource.AppDataSource
import com.jslee.mvvm_testing.data.dataSource.LocalDataSource
import com.jslee.mvvm_testing.data.dataSource.RemoteDataSource
import com.jslee.mvvm_testing.data.local.AppDatabase
import com.jslee.mvvm_testing.data.vo.DevByteVideo
import com.jslee.mvvm_testing.util.api.VideoApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * 네트워크 결과를 가져오고,  데이터베이스를 최신 상태로 유지하는 로직 구현*/
class AppRepository private constructor(application: Application) {

    private var remoteDataSource: AppDataSource
    private var localDataSource: AppDataSource
    private var database: AppDatabase


    companion object {
        @Volatile
        private var INSTANCE: AppRepository? = null

        fun getRepository(app: Application): AppRepository {
            return INSTANCE ?: synchronized(this) {
                AppRepository(app).also {
                    INSTANCE = it
                }
            }
        }
    }
    init {
        database = AppDatabase.getInstance(application)

        remoteDataSource = RemoteDataSource
        localDataSource = LocalDataSource(database.scoreDao, database.userDao, database.videoDao)
    }


    val videos: LiveData<List<DevByteVideo>> = Transformations.map(database.videoDao.getVideos()) {
        it.asDomainModel()
    }


    /**
     * 오프라인 캐시에 저장된 비디오를 Refresh 합니다
     *
     * 이 함수는 IO 디스패처(IO dispatcher)를 사용하여
     * database insert 데이터베이스 작업이 IO dispatcher에서 발생하는지 확인합니다.
     *
     * 'withContext'를 사용하여 IO 디스패처로 전환하면이
     * 함수는 이제 Main 스레드를 포함한 모든 스레드에서 안전하게 호출 할 수 있습니다.
     *
     */
    suspend fun refreshVideos() {
        withContext(Dispatchers.IO) {
            val playlist = VideoApi.devbytes.getPlaylist()

            database.videoDao.insertAll(playlist.asDatabaseModel())
        }
    }

}