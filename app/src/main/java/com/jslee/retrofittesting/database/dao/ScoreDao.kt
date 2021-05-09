package com.jslee.retrofittesting.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.jslee.retrofittesting.database.entity.Score

@Dao
interface ScoreDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertScore(score: Score)

    @Update
    suspend fun updateScore(score: Score)

    @Query("DELETE FROM quiz_score_table")
    suspend fun deleteScore()

    @Query("SELECT * from quiz_score_table WHERE scoreId = :id")
    suspend fun selectScoreByID(id: Long): Score?

    @Query("SELECT * FROM quiz_score_table ORDER BY scoreId DESC LIMIT 1")
    suspend fun selectLatestScore() : Score?

    /**
     * Room은 LiveData를 항상 update된 상태로 유지하므로, 데이터를 한번만 가져오면 된다.**/
    @Query("SELECT * FROM quiz_score_table ORDER BY scoreId DESC")
    fun selectAllScore() : LiveData<List<Score>>

}