package com.example.upintheair

import android.app.Application
import com.example.upintheair.activity_signin.signInModule
import com.example.upintheair.di.applicationModules
import com.example.upintheair.di.repositoryModule
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.android.ext.koin.androidContext

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                applicationModules
            )
        }
    }
}