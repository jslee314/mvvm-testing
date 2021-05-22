package com.jslee.mvvm_testing

import android.app.Application
import com.jslee.mvvm_testing.di.AppComponent
import com.jslee.mvvm_testing.di.DaggerAppComponent


class MyApplication : Application() {

    // Instance of the AppComponent that will be used by all the Activities in the project
    val appComponent: AppComponent by lazy {
        initializeComponent()
    }

    open fun initializeComponent(): AppComponent {
        // Creates an instance of AppComponent using its Factory constructor
        // We pass the applicationContext that will be used as Context in the graph
        return DaggerAppComponent.factory().create(applicationContext)

    }
    override fun onCreate() {
        super.onCreate()
    }
}