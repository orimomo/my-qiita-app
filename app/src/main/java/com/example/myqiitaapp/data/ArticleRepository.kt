package com.example.myqiitaapp.data

import retrofit2.Retrofit

class ArticleRepository(private val retrofit: Retrofit) {
    private val service by lazy { retrofit.create(ArticleService::class.java) }

    suspend fun getItems(page: String = "1", parPage: String = "10"): List<ArticleEntity> {
        return service.getItems(page, parPage).await()
    }
}
