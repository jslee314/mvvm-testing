package com.jslee.mvvm_testing.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quiz_score_table")
data class Score (

    @PrimaryKey(autoGenerate = true)
    var scoreId: Long = 0L,

    @ColumnInfo(name = "start_time")
    var startTime: Long = System.currentTimeMillis(),

    @ColumnInfo(name = "end_time")
    var endTime: Long = startTime,

    @ColumnInfo(name = "the_number_correct_quiz")
    var numRightQuiz: Int = 0

)