package com.example.upintheair.fragment_addwish

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.upintheair.FirestoreDatabase
import com.example.upintheair.entity.Wish
import com.example.upintheair.isOnline
import com.example.upintheair.room.WishesDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class AddWishViewModel(
    private val localRepository: WishesDatabase,
    private val removeRepository: FirestoreDatabase,
    private val context: Context
) : ViewModel(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    val loading = MutableLiveData<Boolean>(false)
    val result = MutableLiveData<String>()
    private var isOnline = isOnline(context)

    fun sendWish(name: String, description: String) {
        val wish = Wish(null, name, description)
        CoroutineScope(coroutineContext).launch {
            loading.postValue(true)
            if (checkWish(wish)) {
                addWishInLocalRepository(wish)

                if (isOnline) {
                    var newWish = localRepository.getLastWish()
                    addWishInRemoveRepository(newWish)
                }

                result.postValue("successes")
            } else {
                result.postValue("error_with_name_wish")
            }
            loading.postValue(false)
        }
    }

    fun checkWish(wish: Wish): Boolean {
        if (wish.name != null && wish.name != "") return true
        return false
    }

    suspend fun addWishInLocalRepository(wish: Wish) {
        CoroutineScope(coroutineContext).async {
            localRepository.createWish(wish)
        }.await()
    }

    fun addWishInRemoveRepository(wish: Wish) = CoroutineScope(coroutineContext).launch {
        removeRepository.createWish(wish)
    }
}