package com.example.upintheair.fragment_addwish

import androidx.lifecycle.ViewModel
import com.example.upintheair.FakeRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

class AddWishViewModel(
    repository: FakeRepository
) : ViewModel(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main
}