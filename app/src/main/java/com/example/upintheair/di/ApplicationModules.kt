package com.example.upintheair.di

import com.example.upintheair.SharedPreferences.preferencesModule
import com.example.upintheair.activity_login.logInModule
import com.example.upintheair.activity_signin.signInModule
import com.example.upintheair.activity_start.startModule
import com.example.upintheair.fragment_addwish.addWishModel
import com.example.upintheair.fragment_wish.wishModule
import com.example.upintheair.fragment_wishlist.wishListModel

val applicationModules = listOf(
    repositoryModule,
    preferencesModule,
    signInModule,
    wishListModel,
    startModule,
    addWishModel,
    wishModule,
    logInModule
)