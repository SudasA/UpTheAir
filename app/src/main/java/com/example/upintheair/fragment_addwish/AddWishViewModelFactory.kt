package com.example.upintheair.fragment_addwish

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.upintheair.FirestoreDatabase
import com.example.upintheair.room.WishesDatabase

@Suppress("UNCHECKED_CAST")
class AddWishViewModelFactory(
    private val localRepository: WishesDatabase,
    private val removeRepository: FirestoreDatabase,
    private val context: Context

) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass == AddWishViewModel::class.java)
            AddWishViewModel(localRepository, removeRepository, context) as T
        else
            throw IllegalArgumentException("Wrong ViewModel class")
    }
}