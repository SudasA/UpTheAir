package com.example.upintheair.activity_signin

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.upintheair.entity.UserRequest
import com.example.upintheair.network.RetrofitRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlin.coroutines.CoroutineContext

class SignInViewModel(
    private val repository: RetrofitRepository
) : ViewModel(), CoroutineScope {
//    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    var errorLiveData = MutableLiveData<String>()
    var tempLiveData = MutableLiveData<String>()

    fun sendUser(
        login: String,
        username: String,
        password: String,
        repeatPassword: String
    ) {
        if (login != "" && username != "" && password != "" && repeatPassword != "") {
            if (checkPassword(password, repeatPassword)) {
                CoroutineScope(coroutineContext).async {
                    val user = UserRequest(login, username, password)
                    postUser(user)
                    errorLiveData.value = null
                }
            } else {
                errorLiveData.value = "error_with_repeat_password"
            }
        } else {
            errorLiveData.value = "error_with_all_edit_text"
        }
    }

    suspend fun postUser(user: UserRequest) {
        repository.getUserService().postUser(user).string()
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    fun checkPassword(p1: String, p2: String): Boolean = p1 == p2
}