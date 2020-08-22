package com.example.upintheair.di

import com.example.upintheair.FirestoreDatabase
import com.example.upintheair.room.WishesDatabase
import com.example.upintheair.network.RetrofitRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { RetrofitRepository() }
    single { WishesDatabase(get()) }
    single { FirestoreDatabase() }
}