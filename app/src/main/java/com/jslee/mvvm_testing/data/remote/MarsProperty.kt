package com.jslee.mvvm_testing.data.remote

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

/**
* @내용 : 이 data 클래스는 ID, 이미지 URL, 유형 (판매 또는 임대) 및 가격 (대여 인 경우 월별)을 포함하는 Mars 속성을 정의함
 *       data 클래스의 속성 이름은 Moshi에서 JSON의 값 이름과 일치시키는 데 사용됨
* @수정 :
* @버젼 : 0.0.0
* @최초작성일 : 2021-05-13 오후 1:40
* @작성자 : 이재선
**/
// 응답 샘플이 다음과 같기때문에 이를 기반으로 data 클래스를 만들자
//[{"price":450000,
//    "id":"424906",
//    "type":"rent",
//    "img_src":"http://mars.jpl.nasa.gov/msl-raw-images/msss/01000/mcam/1000ML0044631300305227E03_DXXX.jpg"},
//...]
@Parcelize
data class MarsProperty (
        val id: String,
        @Json(name = "img_src") val imgSrcUrl: String, // 우리 클래스에서 img_src를 JSON에서 imgSrcUrl로 매핑하는 데 사용
        val type: String,
        val price: Double) : Parcelable {

    val isRental
        get() = type == "rent"
}