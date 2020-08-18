package com.example.upintheair.activity_wish

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.upintheair.LocalRepository
import com.example.upintheair.entity.Wish
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class WishViewModel(private val repository: LocalRepository) : ViewModel(), CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO

    var wish = MutableLiveData<Wish>()

    fun getWish(id: Int) = CoroutineScope(coroutineContext).launch {
//        getWishFromLocalRepository(id)
        val temp = repository.getWish(id)
        wish.postValue(temp)
    }

    fun deleteWish(id: Int) = CoroutineScope(coroutineContext).launch {
        repository.deleteWishById(id)
    }
}