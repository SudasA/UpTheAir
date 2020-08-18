package com.example.upintheair.fragment_addwish

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.upintheair.LocalRepository
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

    val loading = MutableLiveData<Boolean>(false)
    val result = MutableLiveData<String>()

    fun sendWish(name: String, description: String) {
        val wish = Wish(null, name, description)
        CoroutineScope(coroutineContext).launch {
            loading.postValue(true)
            if (checkWish(wish)) {
                addWishInDatabase(wish)
                result.postValue("successes")
            } else{
                result.postValue("error_with_name_wish")
            }
                loading.postValue(false)
        }
    }

    fun checkWish(wish: Wish): Boolean {
        if (wish.name != null && wish.name != "") return true
        return false
    }

    suspend fun addWishInDatabase(wish: Wish) {
        CoroutineScope(coroutineContext).async {
            repository.createWish(wish)
        }.await()
    }
}