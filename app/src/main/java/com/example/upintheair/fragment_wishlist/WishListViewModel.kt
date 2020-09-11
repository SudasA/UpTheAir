package com.example.upintheair.fragment_wishlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.upintheair.entity.Wish
import com.example.upintheair.room.WishesDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
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

    private val _emptyWishList = MutableLiveData<Boolean>(true)
    val emptyWishList: LiveData<Boolean>
        get() = _emptyWishList

    fun getWishList() = launch {
        _emptyWishList.postValue(true)
        getWishListFromDatabase()
    }

    private fun getWishListFromDatabase() {
        val temp = repository.getAllWishes()
        if (temp.isNullOrEmpty() || temp.size == 0)
            _emptyWishList.postValue(true)
        else {
            _emptyWishList.postValue(false)
            _wishList.postValue(temp)
        }
    }
}