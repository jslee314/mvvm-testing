package com.jslee.retrofittesting.rslt_room

import android.app.Application
import androidx.lifecycle.*
import com.jslee.retrofittesting.data.local.dao.ScoreDao
import com.jslee.retrofittesting.data.local.dao.UserDao
import com.jslee.retrofittesting.data.local.entity.Score
import kotlinx.coroutines.launch

/**
* @내용 : 네트워크 호출을 통해 정보를 얻는 기능 포함
 *
* @최초작성일 : 2021-05-13 오후 1:27
* @작성자 : 이재선
**/
class RoomViewModel(val userDataSource: UserDao,
                    val scoreDataSource: ScoreDao,
                    application: Application) : AndroidViewModel(application) {


    /**
     * @내용 : 점수 (캡슐화)
     * @작성자 : 이재선
     **/
    private val _score = MutableLiveData<Score?>()
    val score: LiveData<Score?>
        get() = _score

    val scoreString = Transformations.map(score){_score ->
        _score?.numRightQuiz.toString()

    }

    /**
     * @내용 : 선택 완료 버튼 (캡슐화)
     * @작성자 : 이재선
     **/
    private val _eventClickSuccess = MutableLiveData<Boolean>()
    val eventClickSuccess:LiveData<Boolean>
        get() = _eventClickSuccess

    init {
        getScoreFromRoomDB()
    }

    fun onClickedSuccessBtn(){
        _eventClickSuccess.value = true

    }

    override fun onCleared() {
        super.onCleared()
    }


    /**
     *  viewModelScope 사용 함수들     */
    fun getScoreFromRoomDB(){
        viewModelScope.launch {
            _score.value = getScore()
        }
    }

    /**
     *  DB에 데이터 삽입 삭제 등과 관련된 함수들
     *  이 함수들은 suspend 함수들로
     *  코틀린을 사용해서 비동기 처리를 함*/

    private suspend fun getScore(): Score?{
        val score = scoreDataSource.selectLatestScore()
        return score
    }

}