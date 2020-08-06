package com.example.upintheair.di

import com.example.upintheair.activity_signin.signInModule
import com.example.upintheair.activity_start.startModule
import com.example.upintheair.fragment_addwish.addWishFragment
import com.example.upintheair.fragment_wishlist.wishListFragment

val applicationModules = listOf(
    repositoryModule,
    signInModule,
    wishListFragment,
    startModule,
    addWishFragment
)