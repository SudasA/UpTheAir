package com.example.upintheair.activity_wish

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val wishModule = module {
    viewModel { WishViewModel(get()) }
}