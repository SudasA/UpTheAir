package com.example.upintheair.activity_signin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.upintheair.entity.UserRequest
import com.example.upintheair.md5
import com.example.upintheair.network.RetrofitRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import kotlin.coroutines.CoroutineContext

class SignInViewModel(
    private val repository: RetrofitRepository
) : ViewModel(), CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    private var _error = MutableLiveData<String>(null)
    val error: LiveData<String>
        get() = _error

    private var _loading = MutableLiveData<Boolean>(false)
    val loading: LiveData<Boolean>
        get() = _loading

    fun createUser(
        login: String,
        username: String,
        password: String,
        repeatPassword: String
    ) = launch {
        _loading.postValue(true)

        when {
            login == "" || username == "" || password == "" || repeatPassword == "" ->
                _error.value = "error_with_all_edit_text"
            password.length < 8 ->
                _error.value = "error_with_size_of_password"
            !checkPassword(password, repeatPassword) ->
                _error.value = "error_with_repeat_password"
            else -> {
                val user = UserRequest(login, username, password.md5())
                createNewUser(user)
            }
        }

        _loading.postValue(false)
    }


    private suspend fun createNewUser(user: UserRequest) {
        try {
            repository.getUserService().postUser(user)
            _error.value = "successes"
        } catch (e: HttpException) {
            if (e.code() == 500)
                _error.value = "error_user_exists"
            else
                _error.value = e.message()
        }
        catch (e: Exception) {
            _error.value = e.message
        }
    }

    private fun checkPassword(p1: String, p2: String): Boolean = p1 == p2
}
