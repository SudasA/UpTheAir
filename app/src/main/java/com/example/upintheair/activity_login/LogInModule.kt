package com.example.upintheair.activity_login

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val logInModule = module{
    viewModel { LogInViewModel(get()) }
}