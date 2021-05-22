package com.jslee.mvvm_testing.video
import androidx.lifecycle.ViewModel
import com.jslee.mvvm_testing.di.annotation.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class VideoModule {
    @Binds
    @IntoMap
    @ViewModelKey(VideoViewModel::class)
    abstract fun bindViewModel(viewmodel: VideoViewModel): ViewModel
}
