package com.jslee.mvvm_testing.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User (

    @PrimaryKey(autoGenerate = true)
    var userId: Long = 0L,

    @ColumnInfo(name = "user_name")
    var userName: String = "",

    @ColumnInfo(name = "user_age")
    var userAge: String = ""

)