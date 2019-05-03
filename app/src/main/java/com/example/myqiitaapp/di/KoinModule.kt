package com.example.myqiitaapp.di

import org.koin.dsl.module.module

object KoinModule {
    val modules = listOf(
        dataModule()
    )

    private fun dataModule() = module {

    }
}