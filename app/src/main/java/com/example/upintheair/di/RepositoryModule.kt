package com.example.upintheair.di

import com.example.upintheair.network.RetrofitRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { RetrofitRepository }
//    factory { RetrofitRepository }
}