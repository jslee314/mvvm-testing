package com.jslee.retrofittesting.home

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.jslee.retrofittesting.db.dao.ScoreDao
import com.jslee.retrofittesting.db.dao.UserDao
import com.jslee.retrofittesting.db.entity.User
import kotlinx.coroutines.launch

class HomeViewModel(val userDataSource: UserDao,
                    val scoreDataSource: ScoreDao,
                    application: Application) : AndroidViewModel(application) {

    /**
     *  user 엔티티 데이터 **/
    var user = MutableLiveData<User?>()

    /**
     * 로그인 버튼 클릭여부를 담고 있는 LiveData (캡슐화) **/
    private val _eventClickLogin = MutableLiveData<Boolean>()
    val eventClickLogin:LiveData<Boolean>
        get() = _eventClickLogin

    /**
     * 선택 완료 버튼 클릭여부를 담고 있는 LiveData (캡슐화) **/
    private val _eventClickStart = MutableLiveData<Boolean>()
    val eventClickStart:LiveData<Boolean>
        get() = _eventClickStart


    /**
     * HomeViewModel 객체 생성시 수행되는 함수 */
    init {
       initUser()
    }

    private fun initUser(){
        viewModelScope.launch {
            user.value = getUserFromRoomDB()
        }
    }

    /**
    *  버튼 관련 바인딩 변수    **/
    // user가 set 되었을 때, login 버튼을 보이지 않게 한다.
    val loginVisible = Transformations.map(user) {
        null == it
    }

    // user가 set 되었을 때, START 버튼을 보이게 한다.
    val startVisible = Transformations.map(user) {
        null != it
    }

    /**
     *  버튼 클릭 시 수행되는 함수들     */
    fun onClickedLoginBtn(){
        _eventClickLogin.value = true
        viewModelScope.launch {
//            val value:User? = user.value?:
//            val newUser :User = User(1, user.value.userName,  )
//            insert(value)
        }
    }

    fun onClickedStartBtn(){
        _eventClickStart.value = true
    }

    /**
     *  DB에 데이터 삽입 삭제 등과 관련된 함수들    */
    private suspend fun getUserFromRoomDB(): User?{
        val user:User? = userDataSource.selectLatestUser()
        return user
    }

    private suspend fun insert(user: User) {
        userDataSource.insertUser(user)
    }

    private suspend fun update(user: User) {
        userDataSource.insertUser(user)
    }

    override fun onCleared() {
        super.onCleared()
    }

}