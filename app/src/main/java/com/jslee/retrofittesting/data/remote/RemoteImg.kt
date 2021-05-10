package com.jslee.retrofittesting.data.remote

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RemoteImg(val imgSrcUrl: String, val type:String, val price:Double) : Parcelable {

    val isRental
        get() = type == "rent"


}

//data class RemoteImg(val id:String, @Json(name = "img_src") val imgSrcUrl: String, val type:String, val price:Double) : Parcelable {
//
//    val isRental
//        get() = type == "rent"
//
//
//}