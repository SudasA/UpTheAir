package com.example.upintheair.fragment_addwish

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val addWishFragment = module {
    viewModel { AddWishViewModel(get()) }
}