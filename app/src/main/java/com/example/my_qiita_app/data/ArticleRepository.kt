package com.example.my_qiita_app.data

import retrofit2.Retrofit

class ArticleRepository(private val retrofit: Retrofit) {
    private val service by lazy { retrofit.create(ArticleService::class.java) }

    suspend fun getArticles(page: String = "1", parPage: String = "10"): List<ArticleEntity> {
        return service.getArticles(page, parPage).await()
    }
}
