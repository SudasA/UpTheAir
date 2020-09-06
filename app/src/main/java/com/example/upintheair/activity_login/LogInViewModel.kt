package com.example.upintheair.activity_login

import android.util.Log
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
import kotlin.coroutines.CoroutineContext

class LogInViewModel(
    private val removeRepository: RetrofitRepository,
    private val localRepository: UserPreferences
) : ViewModel(), CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    var error = MutableLiveData<String>(null)
    var loading = MutableLiveData<Boolean>(false)
    var user = MutableLiveData<UserRequest>(null)

    fun authorization(
        login: String?,
        password: String?
    ) = CoroutineScope(coroutineContext).launch {
        loading.postValue(true)
        error.postValue(null)
        user.postValue(null)

        if (checkLoginAndPassword(login, password)) {
            getUser(login!!)
            if (user.value != null) {
                if (password!!.md5() == user.value!!.password) {
                    saveUserData()
                    error.postValue("success")
                } else
                    error.postValue("error_wrong_authorization")
            }
        }
        loading.postValue(false)
    }

    private fun checkLoginAndPassword(login: String?, password: String?): Boolean {
        return if ((login == null || login == "") ||
            (password == null || password == "")
        ) {
            error.postValue("error_with_all_edit_text")
            false
        } else if (login.length < 2 ||
            password.length < 8
        ) {
            error.postValue("error_wrong_authorization")
            false
        } else
            true
    }

    private suspend fun getUser(login: String) = CoroutineScope(coroutineContext).async {
        try {
            var tempUser = removeRepository.getUserService().getUser(login)
            user.postValue(tempUser)
        } catch (e: Exception) {
            Log.e("ERROR", e.message)
            error.postValue("error_wrong_authorization")
        }
    }.await()

    private suspend fun saveUserData() = CoroutineScope(coroutineContext).async {
        localRepository.userLogin = user.value!!.id
        localRepository.userName = user.value!!.username
//        localRepository.saveUserName(user.value!!.username)
    }.await()
}