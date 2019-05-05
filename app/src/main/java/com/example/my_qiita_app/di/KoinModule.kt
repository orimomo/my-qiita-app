package com.example.my_qiita_app.di

import com.example.my_qiita_app.data.ApiClient
import com.example.my_qiita_app.data.ArticleRepository
import com.example.my_qiita_app.domain.ArticleUseCase
import org.koin.dsl.module.module

object KoinModule {
    val modules = listOf(
        dataModule(),
        domainModule()
    )

    private fun dataModule() = module {
        single { ApiClient.retrofit }
        single { ArticleRepository(get()) }
    }

    private fun domainModule() = module {
        single { ArticleUseCase(get()) }
    }
}