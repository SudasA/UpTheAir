package com.example.upintheair.activity_wish

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.upintheair.entity.Wish
import com.example.upintheair.room.WishesDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class WishViewModel(private val repository: WishesDatabase) : ViewModel(), CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO

    private var _wish = MutableLiveData<Wish>()
    val wish: LiveData<Wish>
        get() = _wish

    fun getWish(id: Int) = launch {
//        getWishFromLocalRepository(id)
        val temp = repository.getWish(id)
        _wish.postValue(temp)
    }

    fun deleteWish(id: Int) = launch {
        repository.deleteWishById(id)
    }
}