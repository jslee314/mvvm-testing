package com.jslee.mvvm_testing.util.api

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val BASE_URL = "https://android-kotlin-fun-mars-server.appspot.com/"

/**
 * 지연 초기화 된 Retrofit 서비스를 노출하는
 * 공개 Api 객체[GroundApi]
 * object를 사용하면 싱글턴(Singleton) 패턴이 적용되어 객체가 한번만 생성
 */

object VideoApi {

    // Configure retrofit to parse JSON and use coroutines
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    val devbytes = retrofit.create(VideoService::class.java)
}