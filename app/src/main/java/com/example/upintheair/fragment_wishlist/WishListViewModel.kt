package com.example.upintheair.fragment_wishlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.upintheair.entity.Wish
import com.example.upintheair.room.WishesDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WishListViewModel(
    private val repository: WishesDatabase
) : ViewModel() {

    private val coroutineContext = Dispatchers.IO

    private val _wishList = MutableLiveData<MutableList<Wish>>()
    val wishList: LiveData<MutableList<Wish>>
        get() = _wishList

    private val _emptyWishList = MutableLiveData<Boolean>(false)
    val emptyWishList: LiveData<Boolean>
        get() = _emptyWishList

    fun getWishList() {
        if (_wishList.value != null)
            _wishList.value!!.clear()
        CoroutineScope(coroutineContext).launch {
            _emptyWishList.postValue(false)
            getWishListFromDatabase()
        }
    }

    private fun getWishListFromDatabase() {
        val temp = repository.getAllWishes()
        if (temp.isNullOrEmpty() || temp.size == 0)
            _emptyWishList.postValue(true)
        else {
//            _emptyWishList.postValue(false)
            _wishList.postValue(temp)
        }
    }
}