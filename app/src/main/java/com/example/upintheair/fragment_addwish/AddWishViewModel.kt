package com.example.upintheair.fragment_addwish

import androidx.lifecycle.ViewModel
import com.example.upintheair.FakeRepository
import com.example.upintheair.network.RetrofitRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

class AddWishViewModel(
    repository: RetrofitRepository
) : ViewModel(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    fun sendWish(name: String, description: String){



    }
}