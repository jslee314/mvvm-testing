package com.jslee.mvvm_testing.di

import android.content.Context
import androidx.room.Room
import com.jslee.mvvm_testing.data.AppDataSource
import com.jslee.mvvm_testing.data.local.AppDatabase
import com.jslee.mvvm_testing.data.local.LocalDataSource
import com.jslee.mvvm_testing.data.remote.RemoteDataSource
import com.jslee.mvvm_testing.di.annotation.LocalDataSourceAnnotation
import com.jslee.mvvm_testing.di.annotation.RemoteDataSourceAnnotation
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
object AppModule {

    @JvmStatic
    @Singleton
    @RemoteDataSourceAnnotation
    @Provides
    fun provideRemoteDataSource(
        database: AppDatabase,
        ioDispatcher: CoroutineDispatcher
    ): AppDataSource {
        return RemoteDataSource(
        database.userDao(), database.scoreDao(), database.videoDao(), ioDispatcher
        )
    }

    @JvmStatic
    @Singleton
    @LocalDataSourceAnnotation
    @Provides
    fun provideLocalDataSource(
        database: AppDatabase,
        ioDispatcher: CoroutineDispatcher
    ): AppDataSource {
        return LocalDataSource(
            database.userDao(), database.scoreDao(), database.videoDao(),  ioDispatcher
        )
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideDataBase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "database.db"
        ).build()
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideIoDispatcher() = Dispatchers.IO

}