package com.example.upintheair.activity_signin

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.upintheair.entity.UserRequest
import com.example.upintheair.network.RetrofitRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import okhttp3.internal.wait
import kotlin.coroutines.CoroutineContext

class SignInViewModel(
    private val repository: RetrofitRepository
) : ViewModel(), CoroutineScope {
//    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    var errorLiveData = MutableLiveData<String>()
    var toastMessageLiveData = MutableLiveData<String>()

    fun sendUser(
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
                CoroutineScope(coroutineContext).async {
                    val user = UserRequest(login, username, password)
                    createNewUser(user).wait()
                }
            }
        }
    }

    suspend fun createNewUser(user: UserRequest){
//            val temp = repository.getUserService().postUser(user).string()

        val database = Firebase

        firestore.collection("users")
            .add(
                hashMapOf(
                    "comment" to comment
                )
            )


            errorLiveData.value = null
            toastMessageLiveData.value = "Create user success"
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    fun checkPassword(p1: String, p2: String): Boolean = p1 == p2
}
