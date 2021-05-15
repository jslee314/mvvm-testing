package com.jslee.mvvm_testing.data.vo

import com.squareup.moshi.JsonClass

/**
 * Videos represent a devbyte that can be played.  */
@JsonClass(generateAdapter = true)
data class NetworkVideo(
    val title: String,
    val description: String,
    val url: String,
    val updated: String,
    val thumbnail: String,
    val closedCaptions: String?)