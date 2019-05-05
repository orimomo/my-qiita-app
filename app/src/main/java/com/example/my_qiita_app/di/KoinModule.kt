package com.example.my_qiita_app.di

import com.example.my_qiita_app.data.ApiClient
import com.example.my_qiita_app.data.ArticleRepository
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