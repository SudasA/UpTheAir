package com.example.upintheair.fragment_wishlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.upintheair.FakeRepository

@Suppress("UNCHECKED_CAST")
class WishListViewModelFactory(
    private val repository: FakeRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass == WishListViewModel::class.java)
            WishListViewModel(repository) as T
        else
            throw IllegalArgumentException("Wrong ViewModel class")
    }
}