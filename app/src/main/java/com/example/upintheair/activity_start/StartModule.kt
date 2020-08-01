package com.example.upintheair.activity_start

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val startModule = module{
    viewModel{ StartViewModel(get()) }
}