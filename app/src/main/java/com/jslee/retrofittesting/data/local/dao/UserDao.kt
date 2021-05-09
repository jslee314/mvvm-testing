package com.jslee.retrofittesting.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Query
import com.jslee.retrofittesting.data.local.entity.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(score: User)

    @Update
    suspend fun updateUser(score: User)

    @Query("DELETE FROM user_table")
    suspend fun deleteUser()

    @Query("SELECT * from user_table WHERE user_name = :name")
    suspend fun selectUserByName(name: String): User?

    @Query("SELECT * FROM user_table ORDER BY userId DESC LIMIT 1")
    suspend fun selectLatestUser() : User?

    /**
    * Room은 LiveData를 항상 update된 상태로 유지하므로, 데이터를 한번만 가져오면 된다.**/
    @Query("SELECT * FROM user_table ORDER BY userId DESC")
    fun selectAllUser() : LiveData<List<User>>

}