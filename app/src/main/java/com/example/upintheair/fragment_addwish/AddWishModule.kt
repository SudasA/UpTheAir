package com.example.upintheair.fragment_addwish

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val addWishModel = module {
    viewModel { AddWishViewModel(get(), get(), get()) }
}