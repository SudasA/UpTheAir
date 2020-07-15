package com.example.upintheair.activity_signin

import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.upintheair.entity.UserRequest
import com.example.upintheair.network.RetrofitRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.CoroutineContext

class SignInViewModel(
    private val repository: RetrofitRepository
) : ViewModel(), CoroutineScope {



    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    suspend fun postUser(user: UserRequest) {
        repository.getUserService().postUser(user)
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

}