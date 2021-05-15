package com.jslee.mvvm_testing.util.api

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


private const val BASE_URL = "https://android-kotlin-fun-mars-server.appspot.com/"

/**
 *  Moshi 객체[moshi]와 함께 Moshi 변환기를 사용하여..
 *  Retrofit 객체[retrofit]를 만들기위해
 *  Retrofit.Builder를 사용합니다 */
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

/**
 * 지연 초기화 된 Retrofit 서비스를 노출하는
 * 공개 Api 객체[GroundApi]
 * object를 사용하면 싱글턴(Singleton) 패턴이 적용되어 객체가 한번만 생성
 */
object VideoApi {
//    val retrofitService : RetrofitService by lazy {
//        retrofit.create(RetrofitService::class.java)
//    }

    // Configure retrofit to parse JSON and use coroutines
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://android-kotlin-fun-mars-server.appspot.com/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    val devbytes = retrofit.create(RetrofitService::class.java)
}