package com.example.upintheair.activity_signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.upintheair.network.RetrofitRepository

class SignInViewModelFactory (private val repository: RetrofitRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass == SignInViewModel::class.java)
            SignInViewModel(repository) as T
        else
            throw IllegalArgumentException("Wrong ViewModel class")
    }
}