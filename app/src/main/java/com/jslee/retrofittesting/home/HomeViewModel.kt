package com.jslee.retrofittesting.home

import android.app.Application
import android.util.Log
import android.view.View
import androidx.lifecycle.*
import com.jslee.retrofittesting.database.dao.ScoreDao
import com.jslee.retrofittesting.database.dao.UserDao
import com.jslee.retrofittesting.database.entity.User
import kotlinx.coroutines.launch

class HomeViewModel(val userDataSource: UserDao,
                    val scoreDataSource: ScoreDao,
                    application: Application) : AndroidViewModel(application) {

    /**
     *  user 엔티티 데이터 **/
    var user = MutableLiveData<User?>()

    var mUser:User = User()

    /**
     * 로그인 버튼 클릭여부를 담고 있는 LiveData (캡슐화) **/
    private val _eventClickLogin = MutableLiveData<Boolean>()   // MutableLiveData:  get/set 모두
    val eventClickLogin:LiveData<Boolean>                       // LiveData:   get()만 가능 ( observable를 위한 객체), 항상 UI로 처리, 항상 최신 데이터만 보증
        get() = _eventClickLogin

    /**
     * 선택 완료 버튼 클릭여부를 담고 있는 LiveData (캡슐화) **/
    private val _eventClickStart = MutableLiveData<Boolean>()
    val eventClickStart:LiveData<Boolean>
        get() = _eventClickStart


    /**
     * HomeViewModel 객체 생성시 수행되는 함수 */
    init {
    }


    /**
    *  버튼 관련 바인딩 변수    **/
    // user가 set 되었을 때, login 버튼을 보이지 않게 한다.
//    val loginVisible = Transformations.map(user) {
//        null == it
//    }
////
    // user가 set 되었을 때, START 버튼을 보이게 한다.
    val isVisible = Transformations.map(user) {user->
        checkVisibility(user)
    }
    fun checkVisibility(user: User?): Int {
        if(null != user?.userName) return View.VISIBLE
        else return View.GONE
    }
//
//    val startVisible2 = Transformations.map(user) {
//        null != it
//    }



    val userName = Transformations.map(user) { user ->
        Log.d("jjslee", "user userName: " + user?.userName)
        user?.userName
    }

    val userAge = Transformations.map(user) { user ->
        Log.d("jjslee", "user userAge: " + user?.userAge)
        user?.userAge
    }


    /**
     *  버튼 클릭 시 수행되는 함수들     */
    fun onClickedLoginBtn(){

        _eventClickLogin.value = true


    }

    fun onClickedStartBtn(){
        _eventClickStart.value = true
    }

    /**
     *  viewModelScope 사용 함수들     */
    fun setUserToRoomDB(){
        viewModelScope.launch {
                insert(mUser)
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.launch {
            // Clear the database table.
            clear()

            // And clear tonight since it's no longer in the database
            user.value = null

            // Show a snackbar message, because it's friendly.
            _eventClickLogin.value = false
            _eventClickLogin.value = false

        }
    }


    /**
     *  DB에 데이터 삽입 삭제 등과 관련된 함수들
     *  이 함수들은 suspend 함수들로
     *  코틀린을 사용해서 비동기 처리를 함*/

    private suspend fun getUser(): User?{
        val user:User? = userDataSource.selectLatestUser()
        return user
    }

    private suspend fun insert(user: User) {
        userDataSource.insertUser(user)
    }


    private suspend fun clear() {
        userDataSource.deleteUser()
    }

}