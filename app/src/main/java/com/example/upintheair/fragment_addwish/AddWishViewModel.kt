package com.example.upintheair.fragment_addwish

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.upintheair.LocalRepository
import com.example.upintheair.WishesRoomDatabase
import com.example.upintheair.entity.Wish
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class AddWishViewModel(
    private val repository: LocalRepository
) : ViewModel(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    val loadingLiveData = MutableLiveData<Boolean>(false)
    val goToWishListFragment = MutableLiveData<Boolean>(false)

    fun sendWish(name: String, description: String){
        val wish = Wish(null, name, description)
        CoroutineScope(coroutineContext).launch {
//            loadingLiveData.value = true
            loadingLiveData.postValue(true)

            addWishInDatabase(wish)

            loadingLiveData.postValue(false)
            goToWishListFragment.postValue(true)

        }

    }

    suspend fun addWishInDatabase(wish: Wish){
        CoroutineScope(coroutineContext).async {
            repository.createWish(wish)
        }.await()
    }
}