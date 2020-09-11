package com.example.upintheair.activity_signin

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val signInModule = module {
    viewModel { SignInViewModel(get(), get()) }
}