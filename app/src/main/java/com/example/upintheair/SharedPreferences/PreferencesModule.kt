package com.example.upintheair.SharedPreferences

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val preferencesModule = module {
    single { userPreferences(androidApplication()) }
}

private const val PREFERENCE_FILE_KEY = "com.example.user_preferences"

private fun userPreferences(app: Application): SharedPreferences =
    app.getSharedPreferences(PREFERENCE_FILE_KEY, Context.MODE_PRIVATE)