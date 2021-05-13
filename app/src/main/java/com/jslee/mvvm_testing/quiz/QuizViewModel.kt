package com.jslee.mvvm_testing.quiz

import android.app.Application
import androidx.lifecycle.*
import com.jslee.mvvm_testing.data.local.dao.ScoreDao
import com.jslee.mvvm_testing.data.local.dao.UserDao
import com.jslee.mvvm_testing.data.local.entity.Score
import kotlinx.coroutines.launch

class QuizViewModel(val userDataSource: UserDao,
                    val scoreDataSource: ScoreDao,
                    application: Application) : AndroidViewModel(application) {

    /**
    * @내용 : 점수 (캡슐화)
    * @작성자 : 이재선
    **/
    private val _score = MutableLiveData<Score?>()
    val score:LiveData<Score?>
        get() = _score

    var nowScore = 0
    var startTime = 0L

    /**
    * @내용 : 버튼 클릭여부 (캡슐화)
    * @작성자 : 이재선
    **/
    private val _eventClickToNetworkBtn = MutableLiveData<Boolean>()
    val eventToNetworkBtn:LiveData<Boolean>
        get() = _eventClickToNetworkBtn

    private val _eventClickToRoomBtn = MutableLiveData<Boolean>()
    val eventClickToRoomBtn:LiveData<Boolean>
        get() = _eventClickToRoomBtn


    init {
        startTime = System.currentTimeMillis()
    }

    fun onPlusScore(){
        nowScore = nowScore.plus(1)
    }

    fun onClickedToNetworkBtn(){
        _eventClickToNetworkBtn.value = true
    }

    fun onClickedToRoomBtn(){
        _eventClickToRoomBtn.value = true
    }


    override fun onCleared() {
        super.onCleared()
    }
    /**
     *  viewModelScope 사용 함수들     */
    fun insertToRoomDB(){
        viewModelScope.launch {
//            val score = score.value ?: return@launch
            val score: Score = Score()
            score.numRightQuiz = nowScore
            score.startTime = startTime
            score.endTime = System.currentTimeMillis()
            insert(score)
        }
    }


    /**
     *  DB에 데이터 삽입 삭제 등과 관련된 함수들
     *  이 함수들은 suspend 함수들로
     *  코틀린을 사용해서 비동기 처리를 함*/

    private suspend fun insert(score: Score) {
        scoreDataSource.insertScore(score)
    }
}