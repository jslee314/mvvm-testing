package com.jslee.mvvm_testing.data

import androidx.lifecycle.LiveData
import com.jslee.mvvm_testing.data.local.entity.Score
import com.jslee.mvvm_testing.data.local.entity.User
import com.jslee.mvvm_testing.data.local.entity.Video
import com.jslee.mvvm_testing.data.vo.DevByteVideo

interface AppDataSource {

    suspend fun selectLatestUser() : User?

    suspend fun insertUser(user: User)

    suspend fun deleteUser()

    suspend fun getVideos() : LiveData<List<DevByteVideo>>

    suspend fun insertAll()

    suspend fun insertScore(score: Score)

    suspend fun  selectLatestScore(): Score?


}