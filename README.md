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
           type="com.jslee.mvvm_testing.quiz.QuizViewModel"/>
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

**1. Retrofit를 사용하여  REST 웹 서비스에 요청을확인하고 결과 받아오기**
- Retrofit를 사용하여 인터넷에 연결 후 값을 가져온다. 
- 보통 Retrofit은 실패, 성공 두가지 콜백메서드를 사용해서 결과를 가져온다. 
- 이때 코루틴을 콜백메서드 대신 예외처리만 하면 코루틴을 사용해서 간편하게 구현할 수 있다. 
- 또한 Moshi 라이브러리르 사용하여 Json형식의 응답 respond 구문을 편리하게 사용할 수 있다. 
 > moshi 
 > - JSON 문자열을 Kotlin 객체로 변환하는 Android JSON 파서로, Retrofit에는 Moshi와 함께 작동하는 변환기가 있다. 
 > - 또한 Moshi는 JSON 응답의 키를 동일한 이름을 가진 데이터 개체의 속성과 일치킨다.
 > - 만약 Json 의 key에 다른 속성 이름을 사용하려면 @Json 어노테이션과 JSON key 이름을 추가합니다. 예: @Json(name = "img_src")


**2. 받아온 결과 값 보여주기**
- 받아온 값 중 이미지는 Glide 라이브러리 사용 하여 값을 뿌려준다. 
> Glide 
> - 이미지 관리 프로세스를 단순화할 수 있는 라이브러리
> - 앱에서 이미지를 다운로드, 버퍼링, 디코딩 및 캐시 등의 기능을 제공한다.
> - 인터넷에서 이미지를 로드하기위해서 Glide는 "이미지의 URL"과 "이미지를 넣을 ImageView 객체" 이 두가지가 필요하다. 
> - 이 두가지를 지정하기 위해서는  Glide와 함께 각각 load (),  into () 메서드를 사용한다. 
> - Glide 요청에 옵션을 추가하려면 apply () 메서드를 사용합니다.
> - 예를 들어, placeholder ()와 함께 apply ()를 사용하여 로딩 drawable를 지정하고, error ()와 함께 apply ()를 사용하여 오류drawable를 지정한다.
  
  
- RecyclerView로 결과를 보여준다.  
  리스트의 속성이 변경 될 때 마다 업데이트하려면, RecyclerView와 레이아웃 사이에 Binding adapter를 사용하면 된다. 

> RecyclerView
> - ListView와는 다르게 RecyclerView는 이름에서 알 수 있듯이 재활용이 가능한 뷰이다.
> - ListView는 스크롤할때마다 맨 위의 뷰 객체가 삭제되고, 아래 나타날 객체는 새로 생성하는원리로.. 스크롤을 움직이면 삭제/생성이 반복된다.
> - 반면 RecyclerView는 사용자가 아래로 스크롤 한다고 가정했을 때,
> 맨 위에 존재해서 이제 곧 사라질 뷰 객체를 삭제 하지않고, 아랫쪽에서 새로 나타나날 파란색 뷰 위치로 객체를 이동시킨다.
> - 즉 뷰 객체 자체를 재사용 하는 것인데, 중요한 점은 뷰 객체를 재사용 할 뿐이지 뷰 객체가 담고 있는 데이터(채팅방 이름)는 새로 갱신된다는 것이다.
> 어쨋거나 뷰 객체를 새로 생성하지는 않으므로 효율적인 것이다.
> [ViewHolder] : 스크롤을 내릴때 맨 위에 존재해 사라진 객체는 맨 아래로 이동하여 재활용되고잇는데, 
> 실제 데이터의 갯수가 아닌, 화면에 '보여지는 ** 개' 뷰 객체를 만들어서 가지고 (재활용 하고) 있는것이 ViewHolder 이다.


> Binding adapters(바인딩 어뎁터) 
> - :View와 해당 View에 바인딩 된 data 사이에있는 확장 함수(extension methods)이다.
> - Binding adapter는 데이터가 변경 될 때 사용자 지정 동작을 하게 한다. 
> - (예 : Glide를 호출하여 URL에서 ImageView로 이미지로드)
> - 바인딩 어댑터는 @BindingAdapter 어노테이션을 사용한다.
  

**3. 값을 가져올때 filtering 하기 (HTTP request/respond)**  
- json data의 값에 따라서 특정 이미지 아이콘 GONE 혹은 VISIBLE 결정
  android:visibility="@{property.rental ? View.GONE : View.VISIBLE}"

- **query parameter** (option(filter)) 조건에 따라 RecyclerView에 값 뿌려주기 (필터링)  
  ex) https://android-kotlin-fun-mars-server.appspot.com/realestate?filter=buy

-  RecyclerView 아이템 클릭시 해당 아이템에 대한 자세한 내용을 보는 Fragment로 넘어간다.  
  RecyclerView 항목에 대한 클릭을 처리하는 사용자 정의 리스너 클래스를 만들고 클릭한 position에 있는 아이템 객체를 클래스에 구현한 함수 파라미터로 전달한다. 
  해당 리스너에 RecyclerView 아이템 객체가 전달되면, LiveData에 저장되고,  해당 LiveData 를 옵저빙하던 Observer에 의해 다음 Fragment 로 넘어가게 된다.  
  RecyclerView 아이템 객체를 다음Fragment로 넘길때는, Navigation component의 Safe Args plugin을 사용한다


####  Repository
- **Room을 사용해서 오프라인 캐시(Offline cache) 구현**  
  기본적으로 Retrofit으로 가져온 데이터를 표현하기위해서는 당연히 네트워크 연결이 필요하다.  
  이때, 오프라인 캐싱(Offline cache)을 구현하면, 네트워크 대신 로컬 데이터베이스의 결과를 표시할수 있다.  
  그래서 사용자는 기기가 오프라인 상태이거나 네트워크 연결이 느린 경우에도 앱을 사용할 수 있습니다.  
  Offline cache를 구현하려면 Room 데이터베이스를 사용하여 인터넷에서 가져온 데이터를 기기의 로컬 저장소에 영구 저장해야 한다.  
  
 > 네트워크에서 데이터를 가져올 때 데이터를 즉시 표시하는 대신 데이터베이스에 데이터를 저장한다.
 > 새 네트워크 결과가 수신되면 로컬 데이터베이스를 업데이트하고 로컬 데이터베이스의 화면에 새 콘텐츠를 표시한다.
 > 이로써 오프라인 캐시가 항상 최신 상태를 유지할수 있다.  
 > 또한 기기가 오프라인 인 경우 앱은 로컬에서 캐시 된 데이터를 계속로드 할 수 있다.
 
- **Repository 패턴을 통해 앱의 DATA와 나머지 코드들을 나눔**  

  
  > **Repository  pattern**
  > [Repository] 패턴을 사용하여 Room 데이터베이스에 액세스하고 관리한다.   
  > 이는 [데이터 소스]를 [앱의 나머지]에서 분리하는 "디자인 패턴"이다.  
  > 이 기술은 나머지 앱이 data에 액세스하는 데 사용할 깨끗한 API를 제공한다.   
  > **repository 사용의 장점**
  > - 저장소 모듈은 데이터 작업을 처리하고 여러 백엔드(local, remote 등..)를 사용할 수 있게한다.  
  >  그래서 일반적으로 네트워크에서 데이터를 가져올 지 아니면 로컬 데이터베이스에 캐시 된 결과를 사용할지 결정하기위한 논리를 구현한다.  
  > - 또한 이 패턴은 모듈화하고 테스트 할 수 있다. 리포지토리를 쉽게 mock(가짜로 만듦)하고 나머지 코드를 TEest+ 할 수 있다. 

  
  
  


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










