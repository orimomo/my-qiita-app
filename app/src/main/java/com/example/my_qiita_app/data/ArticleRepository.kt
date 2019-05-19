package com.example.my_qiita_app.data

import retrofit2.Retrofit

class ArticleRepository(private val retrofit: Retrofit) {
    private val service by lazy { retrofit.create(ArticleService::class.java) }

    suspend fun getArticles(page: String, query: String): List<ArticleEntity> {
        return service.getArticlesAsync(page, query) .await()
    }
}
