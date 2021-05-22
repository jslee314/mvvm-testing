package com.jslee.mvvm_testing.di

import android.content.Context
import com.jslee.mvvm_testing.data.AppRepository
import com.jslee.mvvm_testing.home.HomeFragment
import com.jslee.mvvm_testing.home.HomeModule
import com.jslee.mvvm_testing.quiz.QuizFragment
import com.jslee.mvvm_testing.quiz.QuizModule
import com.jslee.mvvm_testing.rslt_network.NetworkFragment
import com.jslee.mvvm_testing.rslt_network.NetworkModule
import com.jslee.mvvm_testing.rslt_room.RoomFragment
import com.jslee.mvvm_testing.video.VideoFragment
import com.jslee.mvvm_testing.video.VideoModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

/**
 * Main component for the application.
 * See the `TestApplicationComponent` used in UI tests.
 */
@Singleton
@Component(
    modules = [
        AppModule::class,
        ViewModelBuilderModule::class,

        HomeModule::class,
        QuizModule::class,
        NetworkModule::class,
        VideoModule::class
    ]
)
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): AppComponent
    }

    fun inject(fragment: HomeFragment)
    fun inject(fragment: QuizFragment)
    fun inject(fragment: RoomFragment)
    fun inject(fragment: NetworkFragment)
    fun inject(fragment: VideoFragment)

    val repository: AppRepository
}
/**
 *  팩토리 패턴
 *  클라이언트 코드로부터 서브 클래스의 인스턴스화를 제거하여 서로 간의 종속성을 낮추고,
 *  결합도를 느슨하게 하며(Loosely Coupled), 확장을 쉽게 합니다.
 */
