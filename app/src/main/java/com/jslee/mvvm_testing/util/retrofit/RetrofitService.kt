package com.jslee.mvvm_testing.util.retrofit

import com.jslee.mvvm_testing.data.remote.MarsProperty
import retrofit2.http.GET

interface RetrofitService {
    /**
     * Coroutine 범위에있는 경우(suspend 함수이므로..)
     * await()로 가져올 수있는 [MarsProperty]의 Coroutine [List]를 반환합니다.*/
    @GET("realestate")
    suspend fun getProperties(): List<MarsProperty>
}