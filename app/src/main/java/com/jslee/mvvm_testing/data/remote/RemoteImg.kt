package com.jslee.mvvm_testing.data.remote

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RemoteImg(

    val imgSrcUrl: String,

    val type:String,

    val price:Double) : Parcelable {

    val isRental
        get() = type == "rent"

}