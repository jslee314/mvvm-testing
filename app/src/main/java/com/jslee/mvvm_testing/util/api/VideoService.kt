package com.jslee.mvvm_testing.util.api

import com.jslee.mvvm_testing.data.converter.NetworkVideoContainer
import retrofit2.http.GET

interface VideoService {
    @GET("devbytes")
    suspend fun getPlaylist(): NetworkVideoContainer
}