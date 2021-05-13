package com.jslee.retrofittesting.data.remote

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
@Parcelize
data class MarsProperty (
        val id: String,
        // 우리 클래스에서 img_src를 JSON에서 imgSrcUrl로 매핑하는 데 사용
        @Json(name = "img_src") val imgSrcUrl: String,
        val type: String,
        val price: Double) : Parcelable {

    val isRental
        get() = type == "rent"
}