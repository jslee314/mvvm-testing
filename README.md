# mvvm-testing



# Architecture components (Clean Code for MVVM)

#### 1) UI Controller과 ViewModel 의 역할을 분명히 나누어야함
- UI Controller 역할 : 
  ViewModel 역할 : 

- ViewModelFactory 사용 : ViewModel의 기본 생성자 외 매개변수가 있는 생성자로 객체를 생성하기 위해

#### 2) UI Controller(lifecycle)는 Observer를 통해 LiveData를 관찰하고잇어서 변경여부를 알 수 있음


#### 3) Databinding, Listenerbinding
```html
  <layout ...>
   <data>
       <variable
           name="miewModel"
           type="com.jslee.retrofittesting.quiz.QuizViewModel"/>
   </data>
   <androidx.constraintlayout..
```
- Data를 View 객체(.xml)에 Data를 직접 연결(Binding)할 수 있음 
- viewmodel를 view에 직접 binding 해서 데이터 뿐 아니라 
     android:text="@{viewModel.user}"
- onClick(), onZoomIn(), or onZoomOut() 과 같은 listener binding을 통해 이벤트도 handling 할 수 있음 
  android:onClick="@{() -> viewModel.onCorrectx()}"



####  Room database and coroutines


####  Connecting to the internet
1. Retrofit를 사용하여  REST 웹 서비스에 요청을확인하고 결과 받아오기
- Retrofit를 사용하여 인터넷에 연결 후 값을 가져온다. 
- 보통 Retrofit은 실패, 성공 두가지 콜백메서드를 사용해서 결과를 가져온다. 
- 이때 코루틴을 콜백메서드 대신 예외처리만 하면 코루틴을 사용해서 간편하게 구현할 수 있다. 
- 또한 Moshi 라이브러리르 사용하여 Json형식의 응답 respond 구문을 편리하게 사용할 수 있다. 
 > moshi 
 > - JSON 문자열을 Kotlin 객체로 변환하는 Android JSON 파서로, Retrofit에는 Moshi와 함께 작동하는 변환기가 있다. 
 > - 또한 Moshi는 JSON 응답의 키를 동일한 이름을 가진 데이터 개체의 속성과 일치킨다.
 > - 만약 Json 의 key에 다른 속성 이름을 사용하려면 @Json 어노테이션과 JSON key 이름을 추가합니다. 예: @Json(name = "img_src")

2. 받아온 결과 값 보여주기
- 받아온 값 중 이미지는 Glide 라이브러리 사용 하여 값을 뿌려준다. 
> Glide 
> - 이미지 관리 프로세스를 단순화할 수 있는 라이브러리
> - 앱에서 이미지를 다운로드, 버퍼링, 디코딩 및 캐시 등의 기능을 제공한다.
> - 인터넷에서 이미지를 로드하기위해서 Glide는 "이미지의 URL"과 "이미지를 넣을 ImageView 객체" 이 두가지가 필요하다. 
> - 이 두가지를 지정하기 위해서는  Glide와 함께 각각 load (),  into () 메서드를 사용한다. 
> - Glide 요청에 옵션을 추가하려면 apply () 메서드를 사용합니다.
> - 예를 들어, placeholder ()와 함께 apply ()를 사용하여 로딩 drawable를 지정하고, error ()와 함께 apply ()를 사용하여 오류drawable를 지정한다.
  
- RecyclerView로 결과를 보여준다. 
- 리스트의 속성이 변경 될 때 마다 업데이트하려면, RecyclerView와 레이아웃 사이에 Binding adapter를 사용하면 된다. 
> Binding adapters(바인딩 어뎁터) 
> - :View와 해당 View에 바인딩 된 data 사이에있는 확장 함수(extension methods)이다.
> - Binding adapter는 데이터가 변경 될 때 사용자 지정 동작을 하게 한다. 
> - (예 : Glide를 호출하여 URL에서 ImageView로 이미지로드)
> - 바인딩 어댑터는 @BindingAdapter 어노테이션을 사용한다.
  


3. 값을 가져올때 filtering 하기 (HTTP request/respond)


####  Repository


--------------------------------------------------------------------------------------------------------
# ANDROID FOR TESTING

#### 1. ViewModel 단위 Testing


#### 2. Repository 단위 Testing
- Fake Data Source 만들기
- Dependency Injection을 사용하여 Test 작성
- Fake Repository 준비하기
- ViewModel 내에서 Fake Repository 사용


#### 3. Fragment 통합 Testing
- 테스트에서 Fragment 사용하기
- ServiceLocator 만들기
- Espresso로 첫 번째 integration Test 작성


####  4. Navigation 통합 Testing
- Mockito를 사용하여 Navigation Test 작성


#### 5. 코루틴 (Coroutines) Testing
- Coroutines and ViewModels
- Testing Coroutine Timing


#### UI Test with Espresso
RecyclerVeiw

----------------------------------------------------------------------------------------------------------

#### 테스팅 전략


#### 읽기쉬운 테스트 작성 전략


#### 햄크레스트


#### 종속성 주입


#### 코루틴










