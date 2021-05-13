package com.jslee.mvvm_testing.util.retrofit

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val BASE_URL = "https://android-kotlin-fun-mars-server.appspot.com/"

/**
 * Retrofit에서 사용할 Moshi 객체[moshi]를 빌드 한다.
 * 이때, 전체 Kotlin 호환성을 위해 Kotlin 어댑터를 추가해야함
 */
private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

/**
 *  Moshi 객체[moshi]와 함께 Moshi 변환기를 사용하여..
 *  Retrofit 객체[retrofit]를 만들기위해
 *  Retrofit.Builder를 사용합니다 */
private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .build()

/**
 * 지연 초기화 된 Retrofit 서비스를 노출하는
 * 공개 Api 객체[RetrofitApi]
 */
object RetrofitApi {
    val retrofitService : RetrofitService by lazy {
            retrofit.create(RetrofitService::class.java)
    }
}