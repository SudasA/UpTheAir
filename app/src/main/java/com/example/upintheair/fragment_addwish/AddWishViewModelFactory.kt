package com.example.upintheair.fragment_addwish

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.upintheair.FirestoreDatabase
import com.example.upintheair.room.WishesDatabase

@Suppress("UNCHECKED_CAST")
class AddWishViewModelFactory(
    private val localRepository: WishesDatabase,
    private val removeRepository: FirestoreDatabase

) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass == AddWishViewModel::class.java)
            AddWishViewModel(localRepository, removeRepository) as T
        else
            throw IllegalArgumentException("Wrong ViewModel class")
    }
}