package com.example.upintheair.fragment_wishlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.upintheair.FakeRepository
import com.example.upintheair.entity.Wish
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class WishListViewModel(
    private val repository: FakeRepository
) : ViewModel(), CoroutineScope {

    val wishListData = MutableLiveData<MutableList<Wish>>()

    fun getWishList() {
//        CoroutineScope(coroutineContext).async {
            wishListData.value = repository.getData()
//        }.await()
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO


}