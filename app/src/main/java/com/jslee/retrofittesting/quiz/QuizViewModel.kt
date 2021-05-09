package com.jslee.retrofittesting.quiz

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.jslee.retrofittesting.database.dao.ScoreDao
import com.jslee.retrofittesting.database.dao.UserDao
import com.jslee.retrofittesting.database.entity.Score
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
    * @내용 : 선택 완료 버튼 (캡슐화)
    * @작성자 : 이재선
    **/
    private val _eventClickFinish = MutableLiveData<Boolean>()
    val eventClickFinish:LiveData<Boolean>
        get() = _eventClickFinish


    init {
        startTime = System.currentTimeMillis()
        Log.d("jjslee", "nowScore : " + nowScore)

    }

    fun onPlusScore(){
        nowScore = nowScore.plus(1)
        Log.d("jjslee", "nowScore : " + nowScore)

    }

    fun onClickedFinish(){
        _eventClickFinish.value = true

    }


    override fun onCleared() {
        super.onCleared()
    }
    /**
     *  viewModelScope 사용 함수들     */
    fun insertToRoomDB(){
        viewModelScope.launch {
//            val score = score.value ?: return@launch
            val score:Score = Score()
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