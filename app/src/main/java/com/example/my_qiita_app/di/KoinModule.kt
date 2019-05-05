package com.example.my_qiita_app.di

import com.example.my_qiita_app.data.ApiClient
import com.example.my_qiita_app.data.ArticleRepository
import com.example.my_qiita_app.domain.ArticleUseCase
import com.example.my_qiita_app.ui.ArticleViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

object KoinModule {
    val modules = listOf(
        dataModule(),
        domainModule(),
        uiModule()
    )

    private fun dataModule() = module {
        single { ApiClient.retrofit }
        single { ArticleRepository(get()) }
    }

    private fun domainModule() = module {
        single { ArticleUseCase(get()) }
    }

    private fun uiModule() = module {
        viewModel { ArticleViewModel(get()) }
    }
}