package com.jslee.mvvm_testing.quiz

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jslee.mvvm_testing.data.local.dao.ScoreDao
import com.jslee.mvvm_testing.data.local.dao.UserDao

class QuizViewModelFactory (private val userDataSource: UserDao,
                            private val scoreDataSource: ScoreDao,
                            private val application: Application) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(QuizViewModel::class.java)) {
            return QuizViewModel(userDataSource, scoreDataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}