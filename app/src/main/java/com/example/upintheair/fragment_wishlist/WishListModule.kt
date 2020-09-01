package com.example.upintheair.fragment_wishlist

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val wishListModel = module {
    viewModel { WishListViewModel(get()) }
}