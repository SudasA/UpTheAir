package com.example.upintheair.activity_wish

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.upintheair.room.WishesDatabase

class WishViewModelFactory (private val repository: WishesDatabase) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass == WishViewModel::class.java)
            WishViewModel(repository) as T
        else
            throw IllegalArgumentException("Wrong ViewModel class")
    }

}