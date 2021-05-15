package com.jslee.mvvm_testing.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jslee.mvvm_testing.data.local.entity.Video

@Dao
interface VideoDao {
    /**
     * DB에서 모든 [video]를 가져옵니다
     * 반환 유형을 LiveData로 변경하여 db의 데이터가 변경 될 때마다
     * UI에 표시된 데이터가 새로 고쳐 지도록함 */

    @Query("select * from video_table")
    fun getVideos(): LiveData<List<Video>>

    /**
     * 네트워크에서 가져온 비디오 목록을 db에 삽입한다.
     * 간단하게 비디오 항목이 이미 데이터베이스에있는 경우 데이터베이스 항목을 덮어 쓴다.
     * 이를 수행하려면 onConflict 인수를 사용하여 충돌 전략을 REPLACE로 설정하면 된다.*/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll( videos: List<Video>)



}