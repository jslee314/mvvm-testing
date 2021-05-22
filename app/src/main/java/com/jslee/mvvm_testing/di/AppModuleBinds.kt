package com.jslee.mvvm_testing.di


import com.jslee.mvvm_testing.data.AppRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class AppModuleBinds {

    @Singleton
    @Binds
    abstract fun bindRepository(repo: AppRepository) : AppRepository
}
