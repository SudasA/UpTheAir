package com.example.upintheair.activity_login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.upintheair.network.RetrofitRepository

class LogInViewModelFactory(
    private val repository: RetrofitRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass == LogInViewModel::class.java)
            LogInViewModel(repository) as T
        else
            throw IllegalArgumentException("Wrong ViewModel class")
    }

}