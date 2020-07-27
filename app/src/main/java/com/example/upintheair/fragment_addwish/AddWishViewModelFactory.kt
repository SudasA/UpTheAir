package com.example.upintheair.fragment_addwish

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.upintheair.FakeRepository

@Suppress("UNCHECKED_CAST")
class AddWishViewModelFactory(
    private val repository: FakeRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass == AddWishViewModel::class.java)
            AddWishViewModel(repository) as T
        else
            throw IllegalArgumentException("Wrong ViewModel class")
    }
}