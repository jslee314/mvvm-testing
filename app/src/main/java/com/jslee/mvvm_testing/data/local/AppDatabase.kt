package com.jslee.mvvm_testing.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jslee.mvvm_testing.data.local.dao.ScoreDao
import com.jslee.mvvm_testing.data.local.dao.UserDao
import com.jslee.mvvm_testing.data.local.dao.VideoDao
import com.jslee.mvvm_testing.data.local.entity.Video
import com.jslee.mvvm_testing.data.local.entity.Score
import com.jslee.mvvm_testing.data.local.entity.User

@Database(entities = [User::class, Score::class, Video::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract val scoreDao: ScoreDao
    abstract val userDao: UserDao
    abstract val videoDao: VideoDao

    // 이 RoomDB 클래스는 DB를 제공할때 한번만 쓰면 되기때문에 인스턴스화 할 필요없이 companion 객체로 만듦
    companion object {

        // db에 대한 private null 가능 변수 INSTANCE를 선언하고 null로 초기화 함
        // 이 변수는 한번 생성되면 DB에 대한 참조를 계속 유지
        // 이렇게 하면 계산비용이 많이드는 DB에 대한 연결을 반복적으로 하지 않아도 됨.
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            synchronized(this) {
                var instance =
                    INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext, AppDatabase::class.java, "database")
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }

}