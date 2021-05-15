package com.jslee.mvvm_testing.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * [Video]는 데이터베이스의 Video 엔티티를 나타냅니다. */
@Entity(tableName = "video_table")
data class Video constructor(
    @PrimaryKey
    val url: String,
    val updated: String,
    val title: String,
    val description: String,
    val thumbnail: String)
