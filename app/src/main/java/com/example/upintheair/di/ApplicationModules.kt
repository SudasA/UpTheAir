package com.example.upintheair.di

import com.example.upintheair.activity_signin.signInModule
import com.example.upintheair.fragment_wishlist.wishListFragment

val applicationModules = listOf(
    repositoryModule,
    signInModule,
    wishListFragment
)