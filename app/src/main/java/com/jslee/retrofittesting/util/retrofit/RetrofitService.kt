package com.jslee.retrofittesting.util.retrofit

import com.jslee.retrofittesting.data.remote.MarsProperty
import retrofit2.http.GET





interface RetrofitService {
    /**
     * Returns a Coroutine [List] of [MarsProperty] which can be fetched with await() if
     * in a Coroutine scope.
     * The @GET annotation indicates that the "realestate" endpoint will be requested with the GET
     * HTTP method
     */
    @GET("realestate")
    suspend fun getProperties(): List<MarsProperty>
}