package com.jslee.mvvm_testing.people

import androidx.lifecycle.ViewModel
import com.jslee.mvvm_testing.di.annotation.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class PeopleModule {
    @Binds
    @IntoMap
    @ViewModelKey(PeopleViewModel::class)
    abstract fun bindViewModel(viewmodel: PeopleViewModel): ViewModel
}