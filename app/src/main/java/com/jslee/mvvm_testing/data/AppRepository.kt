package com.jslee.mvvm_testing.data

import androidx.lifecycle.LiveData
import com.jslee.mvvm_testing.data.local.entity.Score
import com.jslee.mvvm_testing.data.local.entity.User
import com.jslee.mvvm_testing.data.vo.DevByteVideo
import com.jslee.mvvm_testing.di.annotation.LocalDataSourceAnnotation
import com.jslee.mvvm_testing.di.annotation.RemoteDataSourceAnnotation
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * 네트워크 결과를 가져오고,  데이터베이스를 최신 상태로 유지하는 로직 구현*/
class AppRepository @Inject constructor(
    @RemoteDataSourceAnnotation private var remoteDataSource: AppDataSource,
    @LocalDataSourceAnnotation private var localDataSource: AppDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO) {

    suspend fun selectLatestUser(): User? {
        val user: User? = localDataSource.selectLatestUser()
        return user
    }

    suspend fun insertUser(user: User)  {
        localDataSource.insertUser(user)
    }

    suspend fun deleteUser(){
        localDataSource.deleteUser()
    }

    suspend fun insertScore(score: Score) {
        localDataSource.insertScore(score)
    }

    suspend fun selectLatestScore(): Score? {
        return localDataSource.selectLatestScore()
    }

    suspend fun getVideos(): LiveData<List<DevByteVideo>> {
        return localDataSource.getVideos()
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
            localDataSource.insertAll()
        }
    }

}