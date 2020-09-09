package com.example.upintheair.fragment_addwish

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.upintheair.FirestoreDatabase
import com.example.upintheair.entity.Wish
import com.example.upintheair.isOnline
import com.example.upintheair.room.WishesDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class AddWishViewModel(
    private val localRepository: WishesDatabase,
    private val removeRepository: FirestoreDatabase,
    val context: Context
) : ViewModel(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO

    private val _loading = MutableLiveData<Boolean>(false)
    val loading: LiveData<Boolean>
        get() = _loading

    private val _result = MutableLiveData<String>()
    val result: LiveData<String>
        get() = _result

    fun sendWish(name: String, description: String) =
        launch {
            _loading.postValue(true)

            val wish = Wish(null, name, description)
            if (checkWish(wish)) {
                addWishInLocalRepository(wish)
                if (isOnline(context)) {
                    addWishInRemoveRepository(getLastWishFromLocalRepository())
                }

                _result.postValue("successes")
            } else {
                _result.postValue("error_with_name_wish")
            }
            _loading.postValue(false)
    }

    private fun checkWish(wish: Wish): Boolean = wish.name != ""

    private suspend fun addWishInLocalRepository(wish: Wish) = localRepository.createWish(wish)

    private suspend fun getLastWishFromLocalRepository(): Wish = localRepository.getLastWish()

    private fun addWishInRemoveRepository(wish: Wish) = removeRepository.createWish(wish)
}