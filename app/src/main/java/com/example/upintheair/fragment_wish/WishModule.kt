package com.example.upintheair.fragment_wish

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val wishModule = module{
    viewModel { WishViewModel(get()) }
}