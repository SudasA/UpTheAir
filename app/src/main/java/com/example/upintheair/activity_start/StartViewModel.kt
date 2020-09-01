package com.example.upintheair.activity_start

import android.content.Context
import android.content.SharedPreferences
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.os.Build
import android.util.Log
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.upintheair.SharedPreferences.UserPreferences
import com.example.upintheair.network.RetrofitRepository
import com.example.upintheair.network.UserService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

class StartViewModel(
    private val repository: UserPreferences
) : ViewModel(), CoroutineScope {

    val user = MutableLiveData<Boolean>(false)

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    fun checkUser() {
        val temp = repository.userLogin
        if (temp != "")
            user.value = true
    }

}