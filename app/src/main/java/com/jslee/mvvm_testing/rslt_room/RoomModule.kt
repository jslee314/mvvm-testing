package com.jslee.mvvm_testing.rslt_room
import androidx.lifecycle.ViewModel
import com.jslee.mvvm_testing.di.annotation.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class RoomModule {
    @Binds
    @IntoMap
    @ViewModelKey(RoomViewModel::class)
    abstract fun bindViewModel(viewmodel: RoomViewModel): ViewModel
}
