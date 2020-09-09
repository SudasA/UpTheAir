package com.example.upintheair.fragment_wishlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.upintheair.room.WishesDatabase
import com.example.upintheair.entity.Wish
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

@Suppress("UNCHECKED_CAST")
class WishListViewModel(
    private val repository: WishesDatabase
) : ViewModel(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO

    private val _wishList = MutableLiveData<MutableList<Wish>>()
    val wishList: LiveData<MutableList<Wish>>
        get() = _wishList

    fun getWishList() = launch {
            getWishListFromDatabase()
        }

    private fun getWishListFromDatabase() {
            val temp = repository.getAllWishes()
            _wishList.postValue(temp)
    }
}