package com.jslee.mvvm_testing.util.retrofit

import com.jslee.mvvm_testing.data.remote.MarsProperty
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * https://android-kotlin-fun-mars-server.appspot.com/realestate
 * > [{"price":450000,"id":"424905","type":"buy","img_src":"http://mars.jpl.nasa.gov/msl-raw-images/msss/01000/mcam/1000MR0044631300503690E01_DXXX.jpg"},
 * {"price":8000000,"id":"424906","type":"rent","img_src":"http://mars.jpl.nasa.gov/msl-raw-images/msss/01000/mcam/1000ML0044631300305227E03_DXXX.jpg"},
 * {"price":11000000,"id":"424907","type":"rent","img_src":"http://mars.jpl.nasa.gov/msl-raw-images/msss/01000/mcam/1000MR0044631290503689E01_DXXX.jpg"},
 * {"price":8000000,"id":"424908","type":"rent","img_src":"http://mars.jpl.nasa.gov/msl-raw-images/msss/01000/mcam/1000ML0044631290305226E03_DXXX.jpg"},
 * {"price":12000000,"id":"424909","type":"rent","img_src":"http://mars.jpl.nasa.gov/msl-raw-images/msss/01000/mcam/1000MR0044631280503688E0B_DXXX.jpg"},
 * {"price":500000,"id":"424910","type":"buy","img_src":"http://mars.jpl.nasa.gov/msl-raw-images/msss/01000/mcam/1000ML0044631280305225E03_DXXX.jpg"},
 * {"price":450000,"id":"424911","type":"buy","img_src":"http://mars.jpl.nasa.gov/msl-raw-images/msss/01000/mcam/1000MR0044631270503687E03_DXXX.jpg"},
 *
 *
 *
 */



interface RetrofitService {
    /**
     * Coroutine 범위에있는 경우(suspend 함수이므로..)
     * await()로 가져올 수있는 [MarsProperty]의 Coroutine [List]를 반환합니다.
     * https://android-kotlin-fun-mars-server.appspot.com/realestate*/
    @GET("realestate")
    suspend fun getProperties(): List<MarsProperty>


    /** filter type을 buy로 했을때 요청 쿼리 예)
     * https://android-kotlin-fun-mars-server.appspot.com/realestate?filter=buy     */
    @GET("realestate")
    suspend fun getProperties(@Query("filter") type: String): List<MarsProperty>
}