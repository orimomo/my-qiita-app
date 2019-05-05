package com.example.myqiitaapp.di

import com.example.myqiitaapp.data.ApiClient
import com.example.myqiitaapp.data.ArticleRepository
import org.koin.dsl.module.module

object KoinModule {
    val modules = listOf(
        dataModule()
    )

    private fun dataModule() = module {
        single { ApiClient.retrofit }
        single { ArticleRepository(get()) }
    }
}