# retrofitTesting


--------------------------------------------------------------------------------------------------------
Navigation


--------------------------------------------------------------------------------------------------------
Architecture components
1) UI Controller과 ViewModel 의 역할을 분명히 나누어야함
- UI Controller 역할 : 
- ViewModel 역할 : 


- ViewModelFactory 

2) UI Controller(lifecycle)는 Observer를 통해 LiveData를 관찰하고잇어서 변경여부를 알 수 있음


3) Databinding, Listenerbinding
  <layout ...>
   <data>
       <variable
           name="miewModel"
           type="com.jslee.retrofittesting.quiz.QuizViewModel"/>
   </data>
   <androidx.constraintlayout..
- Data를 View 객체(.xml)에 Data를 직접 연결(Binding)할 수 있음 
- viewmodel를 view에 직접 binding 해서 데이터 뿐 아니라 
     android:text="@{viewModel.user}"
- onClick(), onZoomIn(), or onZoomOut() 과 같은 listener binding을 통해 이벤트도 handling 할 수 있음 
  android:onClick="@{() -> viewModel.onCorrectx()}"


--------------------------------------------------------------------------------------------------------
# Room database and coroutines


--------------------------------------------------------------------------------------------------------
# RecyclerView


--------------------------------------------------------------------------------------------------------
# Connecting to the internet


--------------------------------------------------------------------------------------------------------
# Repository


--------------------------------------------------------------------------------------------------------
# 1. ViewModel 단위 테스트

--------------------------------------------------------------------------------------------------------

# 2. Repository 단위 테스트
- Fake Data Source 만들기
- Dependency Injection을 사용하여 Test 작성
- Fake Repository 준비하기
- ViewModel 내에서 Fake Repository 사용


# 3. Fragment 통합 테스트
- 테스트에서 Fragment 사용하기
- ServiceLocator 만들기
- Espresso로 첫 번째 integration Test 작성


# 4. Navigation 통합 테스트
- Mockito를 사용하여 Navigation Test 작성

# 5. 코루틴 테스팅(Coroutines Testint)
- Coroutines and ViewModels
- Testing Coroutine Timing


----------------------------------------------------------------------------------------------------------
# 
테스팅 전략

읽기쉬운 테스트 작성 전략

햄크레스트

종속성 주입

코루틴










