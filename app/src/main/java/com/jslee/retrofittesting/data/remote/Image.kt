package com.jslee.retrofittesting.data.remote

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Image(val id:String, val type:String, val price:Double) : Parcelable {
}