package com.example.my_qiita_app.domain

import com.example.my_qiita_app.data.ArticleEntity
import com.example.my_qiita_app.data.ArticleRepository

class ArticleUseCase(private val repository: ArticleRepository) {
    suspend fun getArticles(page: String): List<ArticleEntity> {
        return repository.getArticles(page)
    }
}