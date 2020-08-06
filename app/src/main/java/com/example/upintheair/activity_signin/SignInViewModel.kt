package com.example.upintheair.activity_signin

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.upintheair.entity.UserRequest
import com.example.upintheair.md5
import com.example.upintheair.network.RetrofitRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class SignInViewModel(
    private val repository: RetrofitRepository
) : ViewModel(), CoroutineScope {
//    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    var errorLiveData = MutableLiveData<String>()

    suspend fun sendUser(
        login: String,
        username: String,
        password: String,
        repeatPassword: String
    ) {

        when {
            login == "" || username == "" || password == "" || repeatPassword == "" ->
                errorLiveData.value = "error_with_all_edit_text"
            password.length < 8 ->
                errorLiveData.value = "error_with_size_of_password"
            !checkPassword(password, repeatPassword) ->
                errorLiveData.value = "error_with_repeat_password"
            else -> {
                withContext(CoroutineScope(coroutineContext).coroutineContext) {
                    val user = UserRequest(login, username, password.md5())
                    createNewUser(user)
                }
            }
        }
    }

    suspend fun createNewUser(user: UserRequest){
            try {
                repository.getUserService().postUser(user)
                errorLiveData.value = "successes"
            } catch (e: Exception) {
                errorLiveData.value = e.message
            }
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    fun checkPassword(p1: String, p2: String): Boolean = p1 == p2
}
