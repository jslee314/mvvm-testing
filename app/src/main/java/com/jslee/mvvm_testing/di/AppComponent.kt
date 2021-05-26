package com.jslee.mvvm_testing.di

import android.content.Context
import com.jslee.mvvm_testing.home.HomeFragment
import com.jslee.mvvm_testing.home.HomeModule
import com.jslee.mvvm_testing.quiz.QuizFragment
import com.jslee.mvvm_testing.quiz.QuizModule
import com.jslee.mvvm_testing.ground.GroundFragment
import com.jslee.mvvm_testing.ground.GroundModule
import com.jslee.mvvm_testing.people.PeopleFragment
import com.jslee.mvvm_testing.people.PeopleModule
import com.jslee.mvvm_testing.video.VideoFragment
import com.jslee.mvvm_testing.video.VideoModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

/**
 * Main component for the application.
 * See the 'TestApplicationComponent' used in UI tests.
 *
 * @Component는 interface 또는 abstract 클래스에 붙일수 있습니다.
 * 컴파일 타임에 애노테이션 프로세서에 의해 생성된 클래스는 접두어 ‘Dagger’와 @Component가 붙은 클래스이름이 합쳐진 형식의 이름을 갖습니다.
 * 예를들면 @Component interface MyComponent{ … }란 인터페이스가 있다면 DaggerMyComponent라는 클래스가 생성되게 됩니다.
 *
 * 컴포넌트 메소드에는 Provision 메소드와 Member-Injection 메소드가 있습니다.
 * Provision Method
 * :프로비전 메소드에는 매개변수가 없고, 모듈이 제공하는 객체의 타입을 반환형으로 갖습니다.
 * 생성된 컴포넌트 클래스에서 이 메소드를 이용하여 객체를 얻을 수 있습니다.
 *
 * Member-Injection Method
 * 의존성을 주입시킬 객체를 메소드의 파라미터로 넘기는 방법입니다.
 * 멤버인젝션 메소드를 호출 하게 되면 타겟 클래스 내의 @Inject 필드에 객체를 주입받게 됩니다.
 *
 */
@Singleton
@Component(
    modules = [
        AppModule::class, // RemoteDataSource, LocalDataSource, AppDatabase, CoroutineDispatcher
        ViewModelBuilderModule::class, // ViewModelProvider.Factory

        HomeModule::class, // HomeViewModel -> ViewModel
        QuizModule::class,
        GroundModule::class,
        PeopleModule::class,
        VideoModule::class
    ])
interface AppComponent {

    // Provision Method : Injection 시킬 객체를 넘기는 Method
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): AppComponent
    }

    // Member-Injection Method : 멤버 파라미터에 의존성 주입 시킬 객체를 넘기는 Method
    //                             인자로 받은 HomeFragment 내부 멤버필드에 의존성 주입
    fun inject(fragment: HomeFragment)
    fun inject(fragment: QuizFragment)
    fun inject(fragment: GroundFragment)
    fun inject(fragment: PeopleFragment)
    fun inject(fragment: VideoFragment)

//    val repository: AppRepository
}

/**
 *  팩토리 패턴
 *  클라이언트 코드로부터 서브 클래스의 인스턴스화를 제거하여 서로 간의 종속성을 낮추고,
 *  결합도를 느슨하게 하며(Loosely Coupled), 확장을 쉽게 합니다.
 */
