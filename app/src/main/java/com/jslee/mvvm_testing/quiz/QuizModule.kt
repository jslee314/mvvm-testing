package com.jslee.mvvm_testing.quiz

import androidx.lifecycle.ViewModel
import com.jslee.mvvm_testing.di.annotation.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class QuizModule {
    @Binds
    @IntoMap
    @ViewModelKey(QuizViewModel::class)
    abstract fun bindViewModel(viewmodel: QuizViewModel): ViewModel
}