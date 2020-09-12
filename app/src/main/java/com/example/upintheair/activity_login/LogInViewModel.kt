package com.example.upintheair.activity_login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.upintheair.SharedPreferences.UserPreferences
import com.example.upintheair.entity.UserRequest
import com.example.upintheair.md5
import com.example.upintheair.network.RetrofitRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import okhttp3.internal.wait
import kotlin.coroutines.CoroutineContext

class LogInViewModel(
    private val removeRepository: RetrofitRepository,
    private val localRepository: UserPreferences
) : ViewModel(), CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO

    private var _error = MutableLiveData<String>(null)
    val error: LiveData<String>
        get() = _error

    private var _loading = MutableLiveData<Boolean>(false)
    val loading: LiveData<Boolean>
        get() = _loading

    private var _user = MutableLiveData<UserRequest>(null)

    fun authorization(
        login: String?,
        password: String?
    ) = launch {
        _loading.postValue(true)
        _error.postValue(null)
        _user.postValue(null)

        if (checkLoginAndPassword(login, password)) {
            getUser(login!!)
            if (checkUser(password!!))
            {
                saveUserData()
                _error.postValue("success")
            }

        }
        _loading.postValue(false)
    }

    private fun checkUser(password: String) : Boolean {
        if (_user.value != null) {
            if (password!!.md5() == _user.value!!.password)
                return true
            else
                _error.postValue("error_wrong_authorization")
        }
        return false
    }

    private fun checkLoginAndPassword(login: String?, password: String?): Boolean {
        return if ((login == null || login == "") ||
            (password == null || password == "")
        ) {
            _error.postValue("error_with_all_edit_text")
            false
        } else if (login.length < 2 ||
            password.length < 8
        ) {
            _error.postValue("error_wrong_authorization")
            false
        } else
            true
    }

    private suspend fun getUser(login: String) = CoroutineScope(coroutineContext).async{
        try {
            _user.postValue(removeRepository.getUserService().getUser(login))
        } catch (e: Exception) {
            Log.e("ERROR", "${e.message}")
            _error.postValue("error_wrong_authorization")
        }
    }.await()

    private fun saveUserData() {
        localRepository.userLogin = _user.value!!.id
        localRepository.userName = _user.value!!.username
    }
}