package com.example.upintheair.fragment_addwish

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.upintheair.WishesRoomDatabase
import com.example.upintheair.entity.Wish
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class AddWishViewModel(
    private val repository: WishesRoomDatabase
) : ViewModel(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    val loadingLiveData = MutableLiveData<Boolean>(false)
//    val wish

    fun sendWish(context: Context, name: String, description: String){
        val wish = Wish(null, name, description)
//        repository.getWishesDao().createWish(wish)
        CoroutineScope(coroutineContext).launch {
            loadingLiveData.value = true

            addWishInDatabase(context, wish)

            loadingLiveData.value = false

        }

    }

    suspend fun addWishInDatabase(context: Context, wish: Wish){
        CoroutineScope(coroutineContext).async {
//            repository.insert(wish)

             val temp = 55
        }.await()
    }
}