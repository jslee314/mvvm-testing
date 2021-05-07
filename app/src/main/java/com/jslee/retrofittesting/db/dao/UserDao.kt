package com.jslee.retrofittesting.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.jslee.retrofittesting.db.entity.User

@Dao
interface UserDao {

    @Insert
    fun insertUser(score: User)

    @Update
    fun updateUser(score: User)

    @Delete
    fun deleteUser()

    @Query("SELECT * from user_table WHERE userId = :id")
    fun selectUserByID(id: Long): User?

    @Query("SELECT * FROM user_table ORDER BY userId DESC LIMIT 1")
    fun selectLatestUser() : User?

    /**
    * Room은 LiveData를 항상 update된 상태로 유지하므로, 데이터를 한번만 가져오면 된다.**/
    @Query("SELECT * FROM user_table ORDER BY userId DESC ")
    fun selectAllUser() : LiveData<List<User>>?

}