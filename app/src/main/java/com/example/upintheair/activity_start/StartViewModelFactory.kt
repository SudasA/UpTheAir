package com.example.upintheair.activity_start

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.upintheair.network.RetrofitRepository

class StartViewModelFactory (private val repository: RetrofitRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass == StartViewModel::class.java)
            StartViewModel(repository) as T
        else
            throw IllegalArgumentException("Wrong ViewModel class")
    }
}