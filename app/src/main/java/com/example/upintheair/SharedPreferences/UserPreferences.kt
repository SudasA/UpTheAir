package com.example.upintheair.SharedPreferences

import android.content.SharedPreferences

class UserPreferences(private val preferences: SharedPreferences) {
    var userName: String by PreferencesDelegate(preferences, USER_NAME, "")
    var userLogin: String by PreferencesDelegate(preferences, USER_LOGIN, "")

    companion object {
        private const val USER_NAME = "user_name"
        private const val USER_LOGIN = "user_login"
    }
}