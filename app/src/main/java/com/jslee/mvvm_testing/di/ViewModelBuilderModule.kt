package com.jslee.mvvm_testing.di

import androidx.lifecycle.ViewModelProvider
import com.jslee.mvvm_testing.util.compose.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelBuilderModule {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}