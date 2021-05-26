package com.jslee.mvvm_testing.home

import android.util.Log
import android.view.View
import androidx.lifecycle.*
import com.jslee.mvvm_testing.data.AppRepository
import com.jslee.mvvm_testing.data.local.entity.User
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * HomeViewModel의 주 생성자에 @Inject를 달아 의존성 주입 대상임을 Dagger에게 알림
 * 주 생성자 반환 Type이 ViewModel 이기 때문에 Component에서 반환타입이 같음 메서드를 찾아서 주입해줌
 */
class HomeViewModel @Inject constructor(
    private val repository: AppRepository
) : ViewModel() {

    /**
     *  user 엔티티 데이터 **/
    var user = MutableLiveData<User?>()

    var mUser: User = User()

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
        start()
    }
    fun start() {

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
        val user: User? = repository.selectLatestUser()
        return user
    }

    private suspend fun insert(user: User) {
        repository.insertUser(user)
    }


    private suspend fun clear() {
        repository.deleteUser()
    }

}