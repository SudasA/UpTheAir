package com.example.upintheair.activity_signin

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.upintheair.entity.UserRequest
import com.example.upintheair.isOnline
import com.example.upintheair.md5
import com.example.upintheair.network.RetrofitRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class SignInViewModel(
    private val repository: RetrofitRepository
) : ViewModel(), CoroutineScope {

    var error = MutableLiveData<String>(null)
    var loading = MutableLiveData<Boolean>(false)

    fun createUser(
        login: String,
        username: String,
        password: String,
        repeatPassword: String
    ) = CoroutineScope(coroutineContext).launch {
        loading.postValue(true)

        when {
            login == "" || username == "" || password == "" || repeatPassword == "" ->
                error.value = "error_with_all_edit_text"
            password.length < 8 ->
                error.value = "error_with_size_of_password"
            !checkPassword(password, repeatPassword) ->
                error.value = "error_with_repeat_password"
            else -> {
                val user = UserRequest(login, username, password.md5())
                createNewUser(user)
            }
        }

        loading.postValue(false)
    }


    suspend fun createNewUser(user: UserRequest) = CoroutineScope(coroutineContext).async {
        try {
            repository.getUserService().postUser(user)
            error.value = "successes"
        } catch (e: Exception) {
            error.value = e.message
        }
    }.await()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    fun checkPassword(p1: String, p2: String): Boolean = p1 == p2
}
