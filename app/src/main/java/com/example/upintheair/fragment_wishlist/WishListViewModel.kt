package com.example.upintheair.fragment_wishlist

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.upintheair.LocalRepository
import com.example.upintheair.entity.Wish
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

@Suppress("UNCHECKED_CAST")
class WishListViewModel(
    private val repository: LocalRepository
) : ViewModel(), CoroutineScope {

    val wishListData = MutableLiveData<MutableList<Wish>>()

    fun getWishList(context: Context) = CoroutineScope(coroutineContext).launch {
            getWishListFromDatabase(context)
        }

    suspend fun getWishListFromDatabase(context: Context) {
        CoroutineScope(coroutineContext).async {
            val temp = repository.getAllWishes()
            wishListData.postValue(temp)
        }.await()
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO


}